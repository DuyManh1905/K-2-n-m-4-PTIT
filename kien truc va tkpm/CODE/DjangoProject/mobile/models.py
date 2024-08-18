from django.db import models

from category.models import Category

class Mobile(models.Model):
    name = models.CharField(max_length=255, unique=True)
    slug = models.SlugField(max_length=255, unique=True,
        help_text='Unique value for product page URL, created from name.', null=True)
    price = models.IntegerField(default=0)
    designer = models.CharField(max_length=255)
    old_price = models.IntegerField(default=0)
    image = models.ImageField(upload_to='product_images', blank=True, null=True)
    is_active = models.BooleanField(default=True)
    is_bestseller = models.BooleanField(default=False)
    description = models.TextField(null=True)
    categories = models.ManyToManyField(Category, related_name='mobiles')
    
    class Meta:
        app_label = 'mobile'
        db_table = 'mobile'
        verbose_name_plural = 'Mobiles'

    def __str__(self):
        return self.name

