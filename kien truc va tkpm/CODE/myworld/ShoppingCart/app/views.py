from django.shortcuts import render
from django.http import HttpResponse
from .models import *
from product.models import Product, product_collection
from product.utils import parse_product_from_mongo
from catalog.models import Category, category_collection
from catalog.utils import parse_category_from_mongo
import re


# Create your views here.
def home(request):
    mongo_data = product_collection.find()
    products = [parse_product_from_mongo(data) for data in mongo_data]

    # products = Product.objects.all()

    context = get_context({'products': products})
    return render(request, 'app/home.html', context)


def checkout(request):
    context = get_context({})
    return render(request, 'app/checkout.html', context)


def catalog(request, category_slug):
    query = {'category': category_slug}
    products_data = product_collection.find(query)

    products = [parse_product_from_mongo(product_data) for product_data in products_data]

    if not products:
        return HttpResponse(product_collection.find())

    context = get_context({'products': products})
    return render(request, 'app/catalog.html', context)


def search(request):
    keyword = ''
    if request.method == 'POST':
        keyword = request.POST.get('keyword')

    product_collection.create_index([('name', 'text'), ('author', 'text'), ('description', 'text')])

    # Truy vấn để tìm kiếm kể cả khi keyword không đầy đủ
    search_keyword = re.compile(f".*{re.escape(keyword)}.*", re.IGNORECASE)
    query = {
        '$or': [
            {'name': {'$regex': search_keyword}},
            {'author': {'$regex': search_keyword}},
            {'description': {'$regex': search_keyword}}
        ]
    }
    products_data = product_collection.find(query)
    products = [parse_product_from_mongo(product_data) for product_data in products_data]

    if not products:
        print(f"No products found with keyword: {keyword}")

    context = get_context({'keyword': keyword, 'products': products})
    return render(request, 'app/search.html', context)


# BASE
def get_base_context():
    mongo_data = category_collection.find()
    categories = [parse_category_from_mongo(data) for data in mongo_data]
    context = {'categories': categories}
    return context


def get_context(dict2):
    res = {**get_base_context(), **dict2}
    return res
