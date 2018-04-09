#include <stdio.h>

void fct(int *arr2);
void swap(int a, int b);
void swap2(int *a, int *b);


int main(void)
{
	int arr2[2][4] = { 1, 2, 3, 4, 5, 6, 7, 8 };
	int(*ptr1)[4] = arr2;	// OK

	int arr1[2] = { 1, 2 };
	int a[3][2] = { 1,2,3,4,5,6 };

	fct(arr1);
	printf("%d \n", arr1[0]);

	{
	    //swap
		int val1 = 10;
		int val2 = 20;
		swap(val1, val2);
		printf("val1 : %d \n", val1);
		printf("val2 : %d \n", val2);
	}


	// swap , call by reference
	{
		int val1 = 10;
		int val2 = 20;
		printf("Before val1 : %d \n", val1);
		printf("Before val2 : %d \n", val2);
		swap2(&val1, &val2);      //val1, val2 주소 전달

		printf("After val1 : %d \n", val1);
		printf("After val2 : %d \n", val2);
	}
	return 0;
}

void fct(int *arr2)
{
	printf("%d \n", arr2[0]);
	arr2[0] = 3;
}

// Call by Value
void swap(int a, int b)
{
	int temp = a;
	a = b;
	b = temp;

	printf("a : %d \n", a);
	printf("b : %d \n", b);
}

void swap2(int *a, int *b)
{
	int temp = *a;
	*a = *b;
	*b = temp;
}


void test1() {
	int a = 10;
	const int * p = &a;
	// *p = 30;    	// Error!
	a = 30; 	          // OK!


}