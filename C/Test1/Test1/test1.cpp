#include<stdio.h>

void test1() {
	int arr[5] = { 1,2 };
	char str[6] = "Hello";
	char str2[5] = { 'H', 'e','l','l','o' };


	char arr1[] = "abc";
	printf("1 : %d\n", sizeof(arr1));	// 4
	char arr2[] = { 'a', 'b', 'c' };
	printf("2 : %d\n", sizeof(arr2));	// 3
	char arr3[] = { 'a', 'b', 'c', '\0' };
	printf("3 : %d\n", sizeof(arr3));	//4
}

void test2() {
	//int *pA = 100;	// pA는 쓰레기 값으로 초기화 됨
}

//void test3() {
//	int a[5] = { 0, 1, 2, 3, 4 };
//
//	printf("%d, %d \n", a[0], a[1]);
//	// a[0]과 a[1]의 주소
//	printf("%d 번지 , %d 번지 \n", &a[0], &a[1]);
//	// 첫 번째 요소의 주소값 이므로 &a[0] 과 같다
//	printf("배열 이름 : %d \n", a);
//}

void test4() {
	int arr[3] = { 0, 1, 2 };
	int *ptr;

	ptr = arr;

	printf("%d, %d, %d \n", ptr[0], ptr[1], ptr[2]);
}

void test5() {
	int* ptr1 = 0;				// int* ptr1=NULL; 과 같은 문장
	char* ptr2 = 0;				// char* ptr2=NULL; 과 같은 문장
	double* ptr3 = 0;	             	// double* ptr3=NULL; 과 같은 문장 

	printf("%d 번지, %d 번지, %d 번지 \n", ptr1++, ptr2++, ptr3++);
	printf("%d 번지, %d 번지, %d 번지 \n", ptr1, ptr2, ptr3);
}

int main(void) {

//	test1();
//	test2();
//	test3();
//	test4();
	test5();

	return 0;
}