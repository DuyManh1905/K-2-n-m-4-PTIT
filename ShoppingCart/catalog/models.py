from django.db import models
from django.contrib.auth.models import User
from db_mongo_connection import db

# Create your models here.
category_collection = db['Category']


class Category:
    def __init__(self, name, slug, description, is_active=True):
        self.name = name
        self.slug = slug
        self.description = description
        self.is_active = is_active
        self.created_at = None  # You can set these values based on your requirements
        self.updated_at = None

    def __str__(self):
        return self.name
