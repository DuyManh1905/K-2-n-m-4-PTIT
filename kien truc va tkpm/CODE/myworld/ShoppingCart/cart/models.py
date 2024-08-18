from django.db import models
from django.contrib.auth.models import User


class Cart(models.Model):
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    active = models.BooleanField(default=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def add_to_cart(self, product_slug):
        cart_item = CartItem.objects.get_or_create(product_slug=product_slug)
        self.items.add(cart_item)
        self.save()

    def remove_from_cart(self, product_slug):
        cart_item = CartItem.objects.get(product_slug=product_slug)
        self.items.remove(cart_item)
        self.save()

    def remove_all_from_cart(self):
        for item in self.items.all():
            self.items.remove(item)
        self.save()



# Create your models here.
class CartItem(models.Model):
    id = models.AutoField(primary_key=True)
    price = models.FloatField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    product_slug = models.CharField(max_length=255, default='')
    cart_id = models.ForeignKey(Cart, on_delete=models.CASCADE, null=True, blank=True, related_name='cart_detail')
    quantity = models.IntegerField(null=False, default=0)
