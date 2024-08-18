from django.shortcuts import render
from django.contrib.auth.decorators import login_required
from django.shortcuts import get_object_or_404, render, redirect

from cart.models import Cart, CartProduct
from product.models import Product

@login_required
def add_to_cart(request, pk):
    product = get_object_or_404(Product, pk=pk)
    quantity = int(request.POST.get('quantity', 1))
    cart, created = Cart.objects.get_or_create(user=request.user)
    cart_product, product_created = CartProduct.objects.get_or_create(cart=cart, product=product)
    cart_product.quantity += quantity
    cart_product.save()
    return redirect('product:detail', pk=pk)

@login_required
def carts(request):
    cart = Cart.objects.filter(user=request.user).first()
    tongtien = 0
    for cproduct in cart.cartproducts.all():
        tongtien += cproduct.product.price * cproduct.quantity
    return render(request, 'cart/carts.html', {
        'cart': cart,
        'tongtien': tongtien,
    })