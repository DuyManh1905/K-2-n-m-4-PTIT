from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('core.urls')),
    path('product/', include('product.urls')),
    path('search/', include('search.urls')),
    path('cart/', include('cart.urls')),
    path('clothes/', include('clothes.urls')),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
