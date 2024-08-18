from product.models import Product
from catalog.models import Category


def create_category_instance(name, slug, description, is_active=True):
    """
    Create an instance of the Category class.
    """
    return Category(name=name, slug=slug, description=description, is_active=is_active)


def parse_category_from_mongo(mongo_data):
    """
    Parse MongoDB data into a Category object.
    """
    category_data = {
        'name': mongo_data.get('name', ''),
        'slug': mongo_data.get('slug', ''),
        'description': mongo_data.get('description', ''),
        'is_active': mongo_data.get('is_active', True),  # Assuming 'is_active' is present in MongoDB data
    }

    # Instantiate the Category class without saving to the database
    category = Category(**category_data)

    return category
