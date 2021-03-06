# composite
# Task. Information handling
Cоздать приложение, разбирающее текст из файла и выполняющее с текстом пять различных операций.  
## Требования  
• Разобранный текст должен быть представлен в виде объекта-структуры, содержащего, например, абзацы, предложения, лексемы, слова, выражения, символы.  
Лексема – часть текста, ограниченная пробельными символами. Для организации структуры данных использовать паттерн Composite.  
• Классы с информацией являются классами сущностей и не должны быть перенагружены методами логики.  
• Исходный текст всегда корректный. То есть, все предложения начинаются с заглавной буквы и завершаются символами «.», «?», «!» или «...».  
Все абзацы начинаются с символа табуляции или заданного числа пробелов, например 4 пробела.  
• Текст из Composite необходимо восстановить в текстовом виде. Пробелы и знаки табуляции при разборе могут заменяться одним пробелом.  
• Для деления текста на составляющие следует использовать регулярные выражения. Не забывать, что регулярные выражения для приложения являются литеральными константами.  
• Код, выполняющий разбиение текста на составляющие части, следует оформить в виде классов-парсеров с использованием паттерна Chain of Responsibility.  
• При разработке парсеров, разбирающих текст, необходимо следить за количеством создаваемых объектов-парсеров. Их не должно быть слишком много.   
• Для записи логов использовать Log4J2.  
• Созданное приложение должно позволять реализовывать функционал по работе над текстом (задачи приведены ниже) без “переписывания” существующего кода.  
• Код должен быть покрыт тестами.  
• Класс с методом main в задании должен отсутствовать. Запуск только с применением тестов.  

## Функциональные возможности, варианты для реализации  
1 Отсортировать абзацы по количеству предложений.  
2 Найти предложения с самым длинным словом.  
3 Удалить из текста все предложения с числом слов меньше заданного.  
4 Найти в тексте все одинаковые слова без учета регистра и посчитать их количество.  
