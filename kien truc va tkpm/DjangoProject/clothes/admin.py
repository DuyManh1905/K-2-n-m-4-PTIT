from django.contrib import admin

from .models import Clothes

class ClothesAdmin(admin.ModelAdmin):
    prepopulated_fields = {"slug": ("name",)}

admin.site.register(Clothes, ClothesAdmin)
