from django.contrib import admin
from django.urls import path, include
from . import views

urlpatterns = [
    path('', views.index, name='product_index'),
    path('init/', views.init, name='init_products'),
    path('add/', views.add_product, name='product_add'),
    path('get_all/', views.get_all_product, name='product_get_all')
]
