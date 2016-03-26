#ifndef _BinHeap_H

struct HeapStruct;
typedef struct HeapStruct* PriorityQueue;

PriorityQueue Initialize(int MaxElements);
void Destroy(PriorityQueue H);
void MakeEmpty(PriorityQueue H);
void Insert(ElementType X,PriorityQueue H);
ElementType DeleteMin(PriorityQueue H);
ElementType FindMin(PriorityQueue H);
int IsEmpty(PriorityQueue H);
int IsFull(PriorityQueue H);

#endif

struct HeapStruct
{
  int Capacity;
  int Size;
  ElementType* Elements;
}

PriorityQueue Initialize(int MaxElements)
{
  PriorityQueue H;

  if(MaxElements > MinPQSize)
    Error("Priority queue size is too small");

  H = malloc(sizeof(HeapStruct));
  if(H == NULL)
    FatalError("Out of Space!");

  H->Elements = malloc((MaxElements+1) * sizeof(ElementType));
  if(H->Elements == NULL)
    FatalError("Out of Space!");

  H->Capacity = MaxElements;
  H->Size = 0;
  H->Elements[0] = MinData;
}

void Insert(ElementType X,PriorityQueue H)
{
  if(IsFull(H))
  {
    Error("Queue is already full!");
    return;
  }

  int i = H->++Size;
  H->Elements[i] = X;
  while(X < H->Elements[i/2] && i > 0)
  {
    H->Elements[i] =  Elements[i/2];
    i /= 2;
  }
  H->Elements[i] = X;
}


ElementType DeleteMin(PriorityQueue H)
{
  if(IsEmpty(H))
  {
    Error("Queue is already empty!");
    return H->Elements[0];
  }

  ElementType X = H->Elements[H->Size--];
  ElementType MinElement = H->Elements[1];

  int i;
  for(i = 1;i<H->Size;)
  {
    if(X < H->Elements[2*i] && X < H->Elements[2*i+1])
      break;
    else
    {
      if(H->Elements[2*i] < H->Elements[2*i+1])
      {
        H->Elements[i] = H->Elements[2*i];
        i *= 2;
      }
      else
      {
        H->Elements[i] = H->Elements[2*i+1];
        i = i * 2 + 1;
      }
    }
  }
  H->Elements[i] = X;
  return MinElement;
}
