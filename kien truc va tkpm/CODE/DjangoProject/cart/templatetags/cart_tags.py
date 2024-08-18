from django import template

from ..models import Cart

register = template.Library()

@register.filter
def mul(value, arg):
    return value * arg

@register.simple_tag
def tongTien(request):
    cart = Cart.objects.filter(user=request.user)
    tongtien = 0
    for cproduct in cart.cartproducts:
        tongtien += cproduct.product.price * cproduct.quantity
    return tongTien
