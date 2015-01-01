#include <stdio.h>
#include "list.h"
#include "list.c"

int main()
{
  struct Node* head = NULL;
  struct Node* second = NULL;
  struct Node* third = NULL;
  struct Node* forth = NULL;
  struct Node* fifth = NULL;

  head = malloc(sizeof(struct Node));
  second = malloc(sizeof(struct Node));
  third = malloc(sizeof(struct Node));
  forth = malloc(sizeof(struct Node));
  fifth = malloc(sizeof(struct Node));

  head->Element = 0;
  head->Next = second;

  second->Element = 2;
  second->Next = third;

  third->Element = 3;
  third->Next = forth;

  forth->Element = 4;
  forth->Next = fifth;

  fifth->Element = 5;
  fifth->Next= NULL;

  List L;
  L = head;
  Position a = Find(2,L);

  printf("%d\n",a->Element);

  Delete(2,L);

  a = Find(2,L);

  if(a==NULL)
    printf("haven't found 2");

    DeleteList(L);

    return 0;
  }
