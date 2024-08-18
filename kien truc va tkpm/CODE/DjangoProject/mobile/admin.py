from django.contrib import admin

from .models import Mobile

class MobileAdmin(admin.ModelAdmin):
    prepopulated_fields = {"slug": ("name",)}

admin.site.register(Mobile, MobileAdmin)
