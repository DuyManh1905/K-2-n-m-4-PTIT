from django.shortcuts import render
from .models import category_collection, Category
from django.http import HttpResponse


# Create your views here.
def init(request):
    records = [
        {
            "name": "Thiếu Nhi",
            "slug": "thieu-nhi",
            "description": "Sách thiếu nhi",
        },
        {
            "name": "Khoa Học",
            "slug": "khoa-hoc",
            "description": "Sách khoa học"
        },
        {
            "name": "Kinh Tế",
            "slug": "kinh-te",
            "description": "Sách kinh tế"
        }
    ]

    category_collection.insert_many(records)

    return HttpResponse("New category is added")
