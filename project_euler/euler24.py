from operator import add

def getPermutations(a):
   if len(a)==1:
      yield a
   else:
      for i in range(len(a)):
         this = a[i]
         rest = a[:i] + a[i+1:]
         for p in getPermutations(rest):
            yield [this] + p

index = 0
for k in getPermutations([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]):
    index += 1
    if index == 1000000:
        print k
        break
