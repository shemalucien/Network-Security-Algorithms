/*this program calculates the Key for two persons using the Diffie Hellman Key exchange algorithm */
#include<stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <conio.h>
// Power function to return value of a ^ b mod P
long long int power(int a,int b,int mod)
{
 long long int t;
 if(b==1)
  return a;
 t=power(a,b/2,mod);
 if(b%2==0)
  return (t*t)%mod;
 else
  return (((t*t)%mod)*a)%mod;
}
//function to check if a number is prime
int prime(int n)
{
	int i,flag = 0,r;
	for (i = 2; i <= n / 2; ++i) 
	{
        // condition for non-prime
        if (n % i == 0) {
            flag = 1;
            break;
        }
    }
    if (n == 1) {
        printf("1 is neither prime nor composite.\n");
        printf("Enter the value of P upon which Alice And Bob both will aggree : \n");
        scanf("%d",&n);
        r=prime(n);
    }
    else {
        if (flag == 0)
        {
		
            printf("%d is a prime number.\n", n);
        }
        else
        {
		
            printf("%d is not a prime number.\n", n);
            printf("Enter the value of P upon which Alice And Bob both will aggree : \n");
            scanf("%d",&n);
            r=prime(n);
        }
    }
}
int main()
{
 int P,G,U,a,V,b;// variable declaration of public and private keys 
 int res=0;
 // both the persons will be agreed upon the common public keys P and P
  printf("Welcome To Diffie-Hellman Key Exchange \n");
 printf("Enter the value of P upon which Alice And Bob both will aggree : \n");
 scanf("%d",&P); // A prime number P is taken 
 res=prime(P);
 printf("Enter the value of  G  upon which Alice And Bob both will aggree:\n ");
 scanf("%d",&G); // A primitve root for P, G is taken 
 // Alice will choose the private key a 
 printf("Enter The private key a for Alice : ");
 scanf("%d",&a);
 //a=rand()%(P-1);// Alice can choose private key randomly
 U=power(G,a,P);// gets the generated key 
// Bob will choose the private key b 
  printf("Enter The private key b for Bob : ");
  scanf("%d",&b);
  //b=rand()%(P-1);// Bob can choose private key randomly
 V=power(G,b,P);// gets the generated key 
 printf("Alice publishes g^a (mod p) : %d\n\n",U); 
 printf("Bob publishes g^b (mod p): %lld\n\n", V); 
 // Generating the secret key after the exchange  of keys 
 printf("Secret key for the Alice is  : %lld\n",power(V,a,P));// Secret key for Alice 
 printf("Secret Key for the Bob is : %lld\n",power(U,b,P)); // Secret key for Bob 
 return 0;
}
