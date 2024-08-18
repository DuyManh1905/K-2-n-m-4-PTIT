from product.models import Product
from .models import Cart, CartItem
from product.models import product_collection
from django.http import HttpResponse
from django.contrib import messages
from django.shortcuts import render, redirect
from product.utils import parse_product_from_mongo
from django.http import JsonResponse
from app.views import get_context


# Create your views here.


def addToCart(request, product_slug):
    try:
        product = product_collection.find({'slug': product_slug})[0]
        cart = Cart.objects.first()
        if not cart:
            cart = Cart(
            )
            cart.save()
        obj, created = CartItem.objects.update_or_create(
            cart_id_id=cart.id,
            product_slug=product_slug,
            defaults={
                'price': product['price'],
            }
        )
        if not created:
            obj.quantity += 1
        else:
            obj.quantity = 1
        obj.save()
        messages.success(request, "Thành công .")
        return redirect(request.META.get('HTTP_REFERER', 'home'))

    except Exception as e:
        return HttpResponse(str(e))


def viewCart(request):
    try:
        cart = Cart.objects.first()
        if not cart:
            cart = Cart(
            )
            cart.save()

        data = []

        for cart_detail in cart.cart_detail.all():
            product = product_collection.find({'slug': cart_detail.product_slug})[0]
            product['_id'] = str(product['_id'])
            data.append({
                'id': cart_detail.id,
                'price': cart_detail.price,
                'quantity': cart_detail.quantity,
                'product': product,
                'sum_price': cart_detail.price * cart_detail.quantity,
            })
        # cartDetail = cart.cart_detail.all()
        return render(request, "cart.html", get_context({"cart": data}))

    except Exception as e:
        return HttpResponse(str(e))


def changeQuantity(request):
    try:
        if request.method == 'GET':
            raise Exception('Not support method')
        cart_detail_id = request.POST.get('id')
        type = request.POST.get('type')

        cartDetail = CartItem.objects.get(pk=cart_detail_id)

        if type == '+':
            cartDetail.quantity += 1
            cartDetail.save()

        else:
            if cartDetail.quantity == 1:
                cartDetail.delete()
            else:
                cartDetail.quantity -= 1
                cartDetail.save()

        messages.success(request, "Thành công .")
        return redirect(request.META.get('HTTP_REFERER', 'cart'))
    except Exception as e:
        return HttpResponse(str(e))
