from django.contrib import admin
from django.urls import path, include
from . import views
from cart import views as cart

urlpatterns = [
    path('', views.home, name="home"),
    path('catalog/<str:category_slug>/', views.catalog, name="catalog"),
    path('cart/index', cart.viewCart, name="cart"),
    path('cart/changeQuantity', cart.changeQuantity, name='changeQuantity'),
    path('checkout/', views.checkout, name="checkout"),
    path('search/', views.search, name="search"),
]