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
	//int *pA = 100;	// pA�� ������ ������ �ʱ�ȭ ��
}

void test3() {
	int a[5] = { 0, 1, 2, 3, 4 };

	printf("%d, %d \n", a[0], a[1]);
	printf("%d ���� , %d ���� \n", &a[0], &a[1]);
	printf("�迭 �̸� : %d \n", a);

}

void test4() {
	int arr[3] = { 0, 1, 2 };
	int *ptr;

	ptr = arr;

	printf("%d, %d, %d \n", ptr[0], ptr[1], ptr[2]);
}

int main(void) {

	test1();
	test2();
	test3();
	test4();

	return 0;
}