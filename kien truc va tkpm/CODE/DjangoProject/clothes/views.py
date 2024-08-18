from django.shortcuts import render, get_object_or_404

from clothes.models import Clothes

def detail(request, pk):
    cloth = get_object_or_404(Clothes, pk=pk)
    print(cloth)
    return render(request, 'clothes/detail.html', {
        'cloth': cloth,
    })
