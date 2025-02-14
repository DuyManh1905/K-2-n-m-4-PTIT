# Generated by Django 4.0.1 on 2024-05-31 18:19

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Appointment',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('doctor_id', models.CharField(max_length=10)),
                ('patient_id', models.CharField(default=0, max_length=10)),
                ('date', models.DateField()),
                ('time', models.CharField(choices=[('morning', 'Morning'), ('noon', 'Noon'), ('afternoon', 'Afternoon'), ('night', 'Night')], max_length=20)),
                ('description', models.CharField(max_length=1000)),
                ('status', models.CharField(choices=[('scheduled', 'Scheduled'), ('completed', 'Completed'), ('cancelled', 'Cancelled')], max_length=50)),
            ],
            options={
                'db_table': 'appointments',
            },
        ),
    ]
