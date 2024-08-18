from anh_proj1 import settings
from catalog.models import Category


def ecomstore(request):
    return {
        "active_categories": Category.objects.filter(is_active=True),
        "site_name": settings.SITE_NAME,
        "meta_keywords": settings.META_KEYWORDS,
        "meta_description": settings.META_DESCRIPTION,
        "request": request,
    }


# # TODO: add 'utils.context_processors.ecomstore' to global_settings.py in site-packages/django/conf/global_settings.py