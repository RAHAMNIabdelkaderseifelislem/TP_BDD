import random
from random import randint

filename = "Data.txt"

def save(firstName,LastName,Code,Address,DateOfBirth):
    file = open(filename,"a")
    file.write("\n"+firstName+"\t,"+LastName+"\t,"+Code+"\t,"+Address+"\t,"+DateOfBirth)
    file.close()

def approve(filename,code):
    f = open(filename,"r")

    lines = f.readlines()
    results = []

    for i in lines:
        results.append(i.split('\t,')[2])
    f.close()
    if code in results:
        return False
    return True
    #print(results)

CapitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
SmallChars ="abcdefghijklmnopqrstuvwxyz"
Numbers = "0123456789"

Chars = CapitalChars+SmallChars
all= Chars+Numbers
j=1
for i in range(1000000):
    day = randint(1,31)
    month = randint(1,12)
    year = randint(1990,2003)
    code = "".join(random.sample(Numbers,8))
    #print(code)
    DateOfBirth =str(day)+"/"+str(month)+"/"+str(year)
    #print(DateOfBirth)
    firstName = "".join(random.sample(Chars,10))
    #print(firstName)
    lastName = "".join(random.sample(Chars,15))
    #print(lastName)
    address = "".join(random.sample(all,5))
    #print(address)
    if approve :
        print("saved ",j," from ",i+1)
        j= j+1
        save(firstName,lastName,code,address,DateOfBirth)

