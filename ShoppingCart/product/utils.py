from product.models import Product
from catalog.models import Category


# Hàm để parse một bản ghi từ MongoDB thành đối tượng Product
def parse_product_from_mongo(mongo_data):
    """
    Parse MongoDB data into a Product object.
    """
    product_data = {
        'name': mongo_data.get('name', ''),
        'author': mongo_data.get('author', ''),
        'slug': mongo_data.get('slug', ''),
        'price': mongo_data.get('price', 0),
        'image': mongo_data.get('image', ''),
        'is_active': mongo_data.get('isActivated', False),
        'is_bestseller': mongo_data.get('isBestSeller', False),
        'quantity': mongo_data.get('quantity', 0),
        'description': mongo_data.get('description', ''),
    }

    # Assuming 'categories' is a list of category names
    category_names = mongo_data.get('categories', [])

    # Instantiate the Product class without saving to the database
    product = Product(**product_data)

    # Convert category names to Category instances (replace with your logic to fetch or create categories)
    categories = [Category(name=category_name) for category_name in category_names]
    product.categories = categories

    return product
