from django.contrib import admin
from django.urls import path, include
from . import views

urlpatterns = [
    path('add_to_cart/<str:product_slug>/',views.addToCart, name='addToCart'),
]