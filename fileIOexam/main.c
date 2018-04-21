#include <stdio.h>
#include <stdlib.h>

int main()
{

    char out_str[100] = {0, };

    FILE *stream;
    stream = fopen("data.txt", "rw");
    fprintf(stream, "abc");
    fputs("ABC", stream);

    fgets(out_str, 40, stream);
    fprintf(stdout, "저장된문장 : %s \n", out_str);

    fclose(stream);

    return 0;
}
