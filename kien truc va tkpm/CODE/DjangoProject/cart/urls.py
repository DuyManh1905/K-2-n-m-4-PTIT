from django.urls import path

from . import views

app_name = 'cart'

urlpatterns = [
    path('<int:pk>/add-to-cart/', views.add_to_cart, name='add_to_cart'),
    path('carts/', views.carts, name='carts'),
]
