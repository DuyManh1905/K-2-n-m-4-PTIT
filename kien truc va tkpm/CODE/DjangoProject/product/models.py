from django.db import models

from category.models import Category

class Product(models.Model):
    name = models.CharField(max_length=255, unique=True)
    slug = models.SlugField(max_length=255, unique=True,
        help_text='Unique value for product page URL, created from name.', null=True)
    author = models.CharField(max_length=255, null=True)
    publisher = models.CharField(max_length=255, null=True)
    price = models.IntegerField(default=0)
    old_price = models.IntegerField(default=0)
    image = models.ImageField(upload_to='product_images', blank=True, null=True)
    is_active = models.BooleanField(default=True)
    is_bestseller = models.BooleanField(default=False)
    description = models.TextField(null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    categories = models.ManyToManyField(Category, related_name='products')

    class Meta:
        app_label = 'product'
        db_table = 'product'
        ordering = ['-created_at']
        verbose_name_plural = 'Products'

    def __str__(self):
        return self.name

