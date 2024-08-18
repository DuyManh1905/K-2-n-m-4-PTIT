from django.db import models
from db_mongo_connection import db
from catalog.models import Category

# Create your models here.
product_collection = db['Product']


class Product:
    def __init__(self, name, author, slug, price, image, is_active=True, is_bestseller=False, quantity=0, description='', categories=None):
        self.name = name
        self.author = author
        self.slug = slug
        self.price = price
        self.image = image
        self.is_active = is_active
        self.is_bestseller = is_bestseller
        self.quantity = quantity
        self.description = description
        self.created_at = None  # You can set these values based on your requirements
        self.updated_at = None
        self.categories = categories or []

    def __str__(self):
        return self.name

