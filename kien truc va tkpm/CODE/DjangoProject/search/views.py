from django.shortcuts import render
from django.db.models import Q

from category.models import Category
from clothes.models import Clothes
from mobile.models import Mobile
from product.models import Product

import speech_recognition as sr

def products(request):
    query = request.GET.get('query', '')
    category_id = request.GET.get('category', 0)
    categories = Category.objects.all()
    products = Product.objects.all()
    clothes = Clothes.objects.all()
    mobiles = Mobile.objects.all()
    
    if query:
        products = products.filter(Q(name__icontains=query))
        clothes = clothes.filter(Q(name__icontains=query))
        mobiles = mobiles.filter(Q(name__icontains=query))
    
    if category_id:
        category = Category.objects.get(pk=category_id)
        products = Product.objects.filter(categories=category)
    
    return render(request, 'search/products.html', {
        'products': products,
        'query': query,
        'clothes': clothes,
        'mobiles': mobiles,
        'categories': categories,
        'category_id': int(category_id),
    })

def productsByVoice(request):
    recognizer = sr.Recognizer()
    
    text = ''

    with sr.Microphone() as source:
        print("Đang nghe... (nói 'kết thúc' để dừng lại)")

        try:
            # Tự động điều chỉnh nền để loại bỏ tiếng ồn
            recognizer.adjust_for_ambient_noise(source)
            
            audio_data = recognizer.listen(source, timeout=2)
            print("Đã nghe xong. Đang nhận dạng...")

            text = recognizer.recognize_google(audio_data, language="vi-VN")
            print("Văn bản nhận dạng từ giọng nói: {}".format(text))

        except sr.UnknownValueError:
            print("Không thể nhận dạng giọng nói")
        except sr.RequestError as e:
            print("Lỗi khi gửi yêu cầu đến API Google: {}".format(e))
        except sr.WaitTimeoutError:
            print("Hết thời gian chờ. Không có âm thanh được nghe.")

    # Kiểm tra nếu text là None hoặc trống thì gán giá trị khoảng trắng
    text = text or ''

    products = Product.objects.all()
    clothes = Clothes.objects.all()
    mobiles = Mobile.objects.all()
    products = products.filter(Q(name__icontains=text))
    clothes = clothes.filter(Q(name__icontains=text))
    mobiles = mobiles.filter(Q(name__icontains=text))
    categories = Category.objects.all()
    category_id = request.GET.get('category', 0)
    
    return render(request, 'search/products.html', {
        'products': products,
        'text': text,
        'clothes': clothes,
        'mobiles': mobiles,
        'categories': categories,
        'category_id': int(category_id),
    })
