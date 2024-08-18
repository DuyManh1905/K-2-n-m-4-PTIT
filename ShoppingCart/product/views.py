from django.shortcuts import render
from .models import product_collection
from django.http import HttpResponse
from .utils import parse_product_from_mongo


# Create your views here.
def init(request):
    records = [
        {
            "name": "Doraemon",
            "author": "Fujiko F. Fujio",
            "slug": "doraemon",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/58/df/82/a6e33f757fb1cc2f2588a3865b67851b.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 100,
            "description": "Doraemon là một chú mèo máy được Sewashi, cháu trai 4 đời sau của Nobita gửi về để chăm "
                           "sóc Nobita. Doraemon được gửi về từ thế kỷ 22 để giúp đỡ Nobita, vì Nobita và cháu nội "
                           "của Doraemon là Sewashi đều bị đối xử tệ hại bởi đời sống khổ sở của tổ tiên Nobita. "
                           "Sewashi tin rằng, nếu Nobita có một cuộc sống tốt đẹp thì cháu nội của Doraemon cũng sẽ "
                           "có một cuộc sống tốt đẹp như vậy. Do đó, Sewashi đã gửi Doraemon về để giúp Nobita có một "
                           "cuộc sống tốt đẹp hơn.",
            "category": ["thieu-nhi"]
        },
        {
            "name": "Conan",
            "author": "Aoyama Gosho",
            "slug": "conan",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/8f/dd/b3/7ee16dc2216cfe0cfc5d517a3220d67a.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 100,
            "description": "Conan là một thám tử học đường, nhưng cậu lại có trí thông minh và khả năng suy luận vượt "
                           "trội. Cậu đã giúp cảnh sát phá được nhiều vụ án khó khăn. Tuy nhiên, trong một lần điều "
                           "tra, cậu đã bị 2 tên ác quỷ đen bí ẩn thuốc mê và ép uống thuốc biến cậu thành một cậu bé "
                           "nhỏ. Sau đó, cậu đã đến nhà giáo sư Agasa, người đã giúp cậu trở về với hình dạng ban "
                           "đầu. Từ đó, cậu đã sống với gia đình của mình dưới danh nghĩa là Edogawa Conan. Cậu sống "
                           "với gia đình của mình và cùng với nhóm thám tử nhí gồm 3 người bạn của mình là: Mori Ran, "
                           "thám tử học đường Sato Miwako và Takagi Wataru. Cùng với đó là những vụ án mà cậu phải "
                           "giải quyết.",
            "category": ["thieu-nhi"]
        },
        {
            "name": "Thuyết tiến hóa",
            "author": "Charles Darwin",
            "slug": "thuyet-tien-hoa",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/a8/4a/df/41bb5e6f32157f56ebcfd19b2737624a.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 48,
            "description": "Thuyết tiến hóa là một lý thuyết khoa học về sự biến đổi của các loài sinh vật qua nhiều "
                           "thế hệ. Thuyết tiến hóa được đề xuất bởi nhà khoa học người Anh Charles Darwin vào năm "
                           "1859 trong cuốn sách Nguồn gốc các loài. Thuyết tiến hóa được coi là một trong những lý "
                           "thuyết quan trọng nhất trong lịch sử khoa học. Thuyết tiến hóa giải thích sự biến đổi của "
                           "các loài sinh vật qua nhiều thế hệ, từ các loài tiền sử đến các loài hiện đại. Thuyết "
                           "tiến hóa cũng giải thích sự đa dạng của các loài sinh vật trên Trái Đất. Thuyết tiến hóa "
                           "được coi là một trong những lý thuyết quan trọng nhất trong lịch sử khoa học. Thuyết tiến "
                           "hóa giải thích sự biến đổi của các loài sinh vật qua nhiều thế hệ, từ các loài tiền sử "
                           "đến các loài hiện đại. Thuyết tiến hóa cũng giải thích sự đa dạng của các loài sinh vật "
                           "trên Trái Đất.",
            "category": ["khoa-hoc"]
        },
        {
            "name": "Khoa học đằng sau những bộ phim kinh điển",
            "author": "Mark Brake",
            "slug": "khoa-hoc-dang-sau-nhung-bo-phim-kinh-dien",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/62/f4/dc/f4924e7a25060fe37b85a0debae2665f.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 24,
            "description": "Khoa học đằng sau những bộ phim kinh điển là một cuốn sách khoa học thú vị, nó giúp bạn "
                           "hiểu rõ hơn về những bộ phim kinh điển mà bạn đã từng xem. Bạn sẽ được tìm hiểu về những "
                           "bộ phim nổi tiếng như: Star Wars, Star Trek, Back to the Future, The Matrix, Avatar, "
                           "Alien, Blade Runner, Jurassic Park, 2001: A Space Odyssey, The Martian, Interstellar, "
                           "Mad Max: Fury Road, Gravity, Inception, A Clockwork Orange, E.T., The Day the Earth Stood "
                           "Still, và nhiều bộ phim khác nữa.",
            "category": ["khoa-hoc"]
        },
        {
            "name": "Kinh tế học hài hước",
            "author": "Dan Ariely",
            "slug": "kinh-te-hoc-hai-huoc",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/4d/07/f8/b7f31766abebfb62e596db1a4b68c9b3.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 24,
            "description": "Kinh tế học hài hước là một cuốn sách về kinh tế học thú vị, nó giúp bạn hiểu rõ hơn về "
                           "những bộ phim kinh điển mà bạn đã từng xem. Bạn sẽ được tìm hiểu về những bộ phim nổi "
                           "tiếng như: Star Wars, Star Trek, Back to the Future, The Matrix, Avatar, Alien, "
                           "Blade Runner, Jurassic Park, 2001: A Space Odyssey, The Martian, Interstellar, "
                           "Mad Max: Fury Road, Gravity, Inception, A Clockwork Orange, E.T., The Day the Earth Stood "
                           "Still, và nhiều bộ phim khác nữa.",
            "category": ["kinh-te"]
        },
        {
            "name": "Kinh tế vĩ mô",
            "author": "Olivier Blanchard",
            "slug": "kinh-te-vi-mo",
            "price": 20000,
            "image": "https://salt.tikicdn.com/cache/750x750/ts/product/29/07/54/2218d748323dff9e9b142b9cc4ae51a2.jpg",
            "isActivated": True,
            "isBestSeller": False,
            "quantity": 14,
            "description": "",
            "category": ["kinh-te"]
        }
    ]
    product_collection.insert_many(records)
    return HttpResponse("New products is added")


def index(request):
    return HttpResponse("<h1>Product App</h1>")


def add_product(request):
    records = {
        "name": "ProductTest",
        "price": 20000,
        "digital": True,
        "image": None
    }

    product_collection.insert_one(records)
    return HttpResponse("New product is added")


def get_all_product(request):
    mongo_data = product_collection.find()

    products = [parse_product_from_mongo(data) for data in mongo_data]
    print(products)
    return HttpResponse(products)
