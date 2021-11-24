#!/usr/bin/env python
# coding: utf-8

# In[1]:


import random #importation of the existing random function in python


# In[2]:


from math import pow # import the power function from the math library


# ##  Calculate the greatest common divisor

# In[4]:


def gcd(a, b):# function declaration with two parameters(a,b) ,DEF keyword
    if a < b: # condition to check if a is less than b 
        return gcd(b, a) # return the greatest common divisor of b and a
    elif a % b == 0: # else if the modulus is equal to zero then
        return b # return b as the gcd 
    else:
        return gcd(b, a % b)# return the gcd of b and a mod b


# ## Generating the random numbers

# In[5]:


def gen_key(q):# function for the key generating 
    key = random.randint(pow(10, 20), q) # variable key holds the result of random integers (by using randint ) between the power of 10 to 20 and value of q
    while gcd(q, key) != 1: # gcd of the value of q and the random integers provided. while they not equal to 1 then
        key = random.randint(pow(10, 20), q) # key has the new value of the random integers

    return key # return the new value of key


# In[6]:


#if a is not relatively prime to y, y is composite
def gen_key_p(a, b, c):# gen_key_p function which holds 3 parameters
    x = 1
    y = a

    while b > 0: #
        if b % 2 == 0:
            x = (x * y) % c
        y = (y * y) % c
        b = int(b / 2)

    return x % c


# In[7]:


#encypt function of the message  
def encrypt(msg, q, h, g):
    en_msg = []

    k = gen_key(q)
    s = gen_key_p(h, k, q)
    c1 = gen_key_p(g, k, q)
    c = []
    #loop to append each character of the string
    for i in range(0, len(msg)):
        en_msg.append(msg[i])
        c.append(msg[i])

    for i in range(0, len(en_msg)):
        en_msg[i] = s * ord(en_msg[i])
        c[i] = int(en_msg[i] / g)
    return en_msg, c1, c


# In[8]:


# decrypt function  of the msg above and the elgamal key
def decrypt(en_msg, c1, key, q):
    dr_msg = []
    h = gen_key_p(c1, key, q)
    for i in range(0, len(en_msg)):
        dr_msg.append(chr(int(en_msg[i] / h)))

    return dr_msg


# In[12]:


def main():
#random
    q = random.randint(pow(10, 20), pow(10, 50))
    g = random.randint(2, q)
    key = gen_key(q)  # Private key for receiver user
    h = gen_key_p(g, key, q)
# recieve the input string from the user 
    message = input("Dear User enter message to encrypt: ")
    en_message, c1, c = encrypt(message, q, h, g)
    #print function to output the encrypted message 
    print(f'encrypted: {"".join(map(chr, c))}')
    dr_message = decrypt(en_message, c1, key, q)
    d_message = ''.join(dr_message)
          #print the message which is decypted 
    print("Decrypted Message :", d_message)


if __name__ == '__main__':
    main()


# In[ ]:




