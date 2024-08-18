from django.db import models
from django.contrib.auth.models import User
from product.models import Product


class Customer(models.Model):
    user = models.OneToOneField(User, null=True, blank=False,
                                on_delete=models.SET_NULL)  # OneToOneField: One user has one customer
    name = models.CharField(max_length=200, null=True)
    email = models.CharField(max_length=200, null=True)

    def __str__(self):
        return self.name
