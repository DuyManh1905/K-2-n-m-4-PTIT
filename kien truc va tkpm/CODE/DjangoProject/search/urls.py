from django.contrib import admin
from django.urls import path

from . import views

app_name = 'search'

urlpatterns = [
    path('', views.products, name='products'),
    path('voice/', views.productsByVoice, name='products-voice'),
]
