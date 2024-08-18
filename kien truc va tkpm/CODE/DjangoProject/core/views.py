from django.shortcuts import render
from category.models import Category

from clothes.models import Clothes
from mobile.models import Mobile
from product.models import Product

def index(request):
    products = Product.objects.all()
    clothes = Clothes.objects.all()
    mobiles = Mobile.objects.all()
    categories = Category.objects.all()
    return render(request, 'index.html', {
        'products': products,
        'clothes': clothes,
        'mobiles': mobiles,
        'categories': categories, 
    })


