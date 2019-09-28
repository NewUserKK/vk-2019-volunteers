# coding=utf-8
import random

def generate_random(array):
  a = random.randint(0, len(array) - 1)
  return array[a]

def generate_phone():
  a = '+7'
  for i in range(10):
    a += str(random.randint(0, 9))
  return a

def generate_login():
  a = ''
  l = random.randint(5, 10)
  for i in range(l):
    a += chr(ord('a') + random.randint(0,25))
  return a

maleName = ['Павел', 'Андрей', 'Егор', 'Владимир', 'Олег', 'Владислав',
            'Артём', 'Степан', 'Ярослав', 'Алексей', 'Александр', 'Борис',
            'Георгий', 'Григорий', 'Антон', 'Дмитрий', 'Сергей', 'Николай',
            'Константин', 'Василий', 'Виталий', 'Виктор', 'Юрий', 'Пётр',
            'Максим', 'Михаил', 'Леонид', 'Илья']
maleSurname = ['Калугин', 'Назаров', 'Акулов', 'Белов', 'Белоус',
               'Печенев', 'Кузнецов', 'Соколов', 'Иванов', 'Петров',
               'Сидоров', 'Харченко', 'Смирнов', 'Гилёв', 'Старцев', 'Каменев',
               'Афанасьев', 'Прокофьев', 'Коробков', 'Кокорин', 'Земсков', 'Корнеев',
               'Сорокин', 'Мельников', 'Комаров', 'Захаров']
malePat = ['Александрович', 'Евгеньевич', 'Ильич', 'Юрьевич', 'Афанасьевич',
           'Сергеевич', 'Николаевич', 'Станиславович', 'Алексеевич', 'Игоревич',
           'Иванович', 'Всеволодович', 'Игнатьевич', 'Петрович', 'Борисович',
           'Владимирович', 'Викторович', 'Михайлович', 'Сергеевич']
femaleName = ['Валентина', 'Ксения', 'Маргарита', 'Анна', 'Марина', 'Татьяна', 'Юлия',
              'Наталья', 'Ольга', 'Надежда', 'Диана', 'Екатерина', 'Анастасия',
              'Елена', 'Ирина', 'Софья', 'Алёна', 'Лидия', 'Галина', 'Дарья',
              'Мария', 'Александра', 'Евгения', 'Елизавета', 'Виктория',
              'Василиса']
femaleSurname = ['Родионова', 'Дроздова', 'Фёдорова', 'Солнцева', 'Николаева',
                 'Данилова', 'Яковлева', 'Никифорова', 'Зайцева', 'Иванова',
                 'Петрова', 'Букушкина', 'Пономарёва', 'Фролова', 'Волкова',
                 'Минакина', 'Ларионова', 'Прохорова', 'Болотова', 'Поварова',
                 'Исаева', 'Сейферт', 'Родина']
femalePat = ['Анатольевна', 'Валерьевна', 'Ивановна', 'Александровна',
             'Ильинична', 'Леонидовна', 'Михайловна', 'Николаевна', 'Антоновна',
             'Игоревна', 'Алексеевна', 'Петровна', 'Юрьевна', 'Сергеевна',
             'Васильевна', 'Павловна', 'Борисовна', 'Викторовна']
roles = ['Админ', 'Админ_ка', 'Холл-менеджер', 'Куратор', 'Руководитель']
cities = ['Санкт-Петербург', 'Новосибирск', 'Москва']
locations = ['Ул.Гжатская', 'Ул.Курчатова', 'Кронверский пр.', 'Вяземский пр.']
buildings = ['5-7', '49', '21a-2', '16', '6']
hours = ['10:00 - 18:00', '9:00 - 16:00', '8:00 - 20:00']
photos = ['pornhub.com', 'brazzers.com', 'vk.com', 'yandex.ru']
emails = ['example@hotmail.com', 'example@yandex.ru', 'example@gmail.com',
          'example@mail.ru', 'example@rambler.com', 'example@yahoo.com',
          'example@outlook.com', 'example@niuitmo.ru', 'example@rain.ifmo.ru']
educations_or_employments = ['NEET', 'ITMO University', 'Yandex', 'NAUKA', 'Google', 'NEERC', 'Reddle', 'Amazon', 'Huawei']
specializations = ['Программист', 'Фронтендер', 'Дизайнер', 'Инженер', 'Водонос', 'Строитель', 'Тренер']
sizes = ['xxxs', 'xxs', 'xs', 's', 'm', 'l', 'xl', 'xxl', 'xxxl']
types = ['Выставка', 'Презентация', 'Экскурсия', 'Праздник']

def seed(f):
  template = "INSERT INTO ROLE(name) VALUES('%s');\n"
  roles_length = len(roles)
  for i in range(roles_length):
    f.write(template % (roles[i]))
  template = "INSERT INTO MUSEUM(city, location, building, hours, photo, name) VALUES('%s', '%s', '%s', '%s', '%s', 'Политех');\n"
  museums_length = random.randint(10, 30)
  for i in range(museums_length):
    f.write(template % (generate_random(cities), generate_random(locations), generate_random(buildings), generate_random(hours), generate_random(photos)))
  template = "INSERT INTO STAFF(name, surname, patronymic, email, phone, photo_link, birthday, login, password) VALUES('%s', '%s', '%s', '%s', '%s', '%s', current_timestamp, '%s', crypt('qwerty', gen_salt('bf', 8)));\n"
  staff_length = random.randint(10, 30)
  for _ in range(staff_length):
    if random.randint(0, 1):
      # insert male
      f.write(template % (
        generate_random(maleName), generate_random(maleSurname),
        generate_random(malePat), generate_random(emails),
        generate_phone(), generate_random(photos), generate_login()))
    else:
      # insert female
      f.write(template % (
              generate_random(femaleName), generate_random(femaleSurname),
              generate_random(femalePat), generate_random(emails),
              generate_phone(), generate_random(photos), generate_login()))
  template = "INSERT INTO VOLUNTEER(name, surname, patronymic, birthday, social_link, education_or_employment, specialization, has_volunteering_experience, volunteer_experience_description, has_children_experience, children_experience_description, additional_skills, expectations, has_allergies, has_food_preferences, size, photo_link, source_volunteer_program, why_interesting, notify, login, password, email, phone) VALUES('%s', '%s', '%s', current_timestamp, '%s', '%s', '%s', %s, '%s', %s, '%s', '%s', '%s', %s, %s, '%s', '%s', '%s', '%s', %s, '%s', crypt('qwerty', gen_salt('bf', 8)), '%s', '%s');\n"
  volunteers_length = random.randint(30, 80)
  for _ in range(volunteers_length):
    if random.randint(0, 1):
      # insert male
      f.write(template % (
        generate_random(maleName), generate_random(maleSurname),
        generate_random(malePat), generate_random(photos),
        generate_random(educations_or_employments), generate_random(specializations),
         random.randint(0,1) == 1, "Lorem ipsum dolor sit amet consectetur",
         random.randint(0,1) == 1, "Ach ich sehe itzt da ich zur hochzeit gehe",
         "Дотер, осер, смузихлеб", "Нормально делаем - нормально будет",
         random.randint(0,1) == 1, random.randint(0,1) == 1 , generate_random(sizes),
         generate_random(photos), "Из яндекс.новостей", "Потому что я волонтер с детства",
         random.randint(0,1) == 1, generate_login(), generate_random(emails), generate_phone()))
    else:
      # insert female
      f.write(template % (
        generate_random(femaleName), generate_random(femaleSurname),
        generate_random(femalePat), generate_random(photos),
        generate_random(educations_or_employments), generate_random(specializations),
         random.randint(0,1) == 1, "Lorem ipsum dolor sit amet consectetur",
         random.randint(0,1) == 1, "Ach ich sehe itzt da ich zur hochzeit gehe",
         "Дотер, осер, смузихлеб", "Нормально делаем - нормально будет",
         random.randint(0,1) == 1, random.randint(0,1) == 1 , generate_random(sizes),
         generate_random(photos), "Из яндекс.новостей", "Потому что я волонтер с детства",
         random.randint(0,1) == 1, generate_login(), generate_random(emails), generate_phone()))
  template = "INSERT INTO EVENT(museum_id, start_date, end_date, volunteers_required, volunteers_present, type, photo_link, title, description, link_to_event, finished) VALUES (%d, current_timestamp, current_timestamp, %d, %d, '%s', '%s', '%s', '%s', '%s', %s);\n"
  events_length = random.randint(100, 300)
  for _ in range(events_length):
    f.write(template % (random.randint(1, museums_length), random.randint(1,30), random.randint(1,30), generate_random(types), generate_random(photos), generate_login(), generate_login(), generate_random(photos), random.randint(0,1) == 1))

def init(f):
  f.write("DELETE FROM ROLE;\n")
  f.write("DELETE FROM MUSEUM;\n")
  f.write("DELETE FROM STAFF;\n")
  f.write("DELETE FROM VOLUNTEER;\n")
  f.write("DELETE FROM EVENT;\n")
  f.write("ALTER SEQUENCE role_id_seq RESTART;\n")
  f.write("ALTER SEQUENCE museum_id_seq RESTART;\n")
  f.write("ALTER SEQUENCE staff_id_seq RESTART;\n")
  f.write("ALTER SEQUENCE volunteer_id_seq RESTART;\n")
  f.write("ALTER SEQUENCE event_id_seq RESTART;\n")


def main():
  f = open("data.sql", "w+", encoding="utf8")
  init(f)
  seed(f)
  f.close()


if __name__ == "__main__":
  main()
