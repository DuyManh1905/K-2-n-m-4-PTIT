from django import forms
from django.contrib import admin
from .models import Category, Product


class ProductAdminForm(forms.ModelForm):
    class Meta:
        model = Product
        fields = "__all__"

    def clean_price(self):
        price = self.cleaned_data.get("price")
        if price <= 0:
            raise forms.ValidationError("Price must be greater than 0")
        return price

    # def clean_quantity(self):
    #     quantity = self.cleaned_data.get("quantity")
    #     if quantity <= 0:
    #         raise forms.ValidationError("Quantity must be greater than 0")
    #     return quantity

    # def clean(self):
    #     cleaned_data = super().clean()
    #     price = cleaned_data.get("price")
    #     quantity = cleaned_data.get("quantity")
    #     if price and quantity:
    #         cleaned_data["total"] = price * quantity
    #     return cleaned_data