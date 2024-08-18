from django.db import models
from django.contrib.auth.models import User

from product.models import Product

class Cart(models.Model):
    date_added = models.DateTimeField(auto_now_add=True) 
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    class Meta:
        app_label = 'cart'
        db_table = 'cart'
        ordering = ['-date_added']
        verbose_name_plural = 'Carts'

    def __str__(self):
        return str(self.user.username)
    
class CartProduct(models.Model):
    cart = models.ForeignKey(Cart, related_name='cartproducts', on_delete=models.CASCADE)
    product = models.ForeignKey(Product, on_delete=models.CASCADE) 
    quantity = models.IntegerField(default=1)
    
    class Meta:
        app_label = 'cart'
        db_table = 'cart_product'
        verbose_name_plural = 'CartProducts'
        