from django.shortcuts import render, get_object_or_404

from product.models import Product

def detail(request, pk):
    product = get_object_or_404(Product, pk=pk)
    related_products = product.categories.first().products.exclude(pk=pk)
    return render(request, 'product/detail.html', {
        'product': product,
        'related_products': related_products,
    })
