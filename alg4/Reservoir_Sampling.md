# Reservoir Sampling, Sampling from a stream of elements

## Problem Statement

Reservoir Sampling is an algorithm for sampling elements from a stream of data. Imagine you are given a really large stream of data elements, for example:

> * Queries on google searches in May
> * Products bought at Walmart during the Christmas season
> * Names in a phone book

Your goal is to efficiently return a random sample of 1,000 elements evenly distributed from the original stream. How would you do it?

The right answer is generating random integers between **0** and **N - 1**, then retrieving the elements at those indices and you have your answer. If you need to be generate unique elements, then just throw away indices you've already generated.

So, let me make the problem harder. You don't know **N** (the size of the stream) in advance and you can't index directly into it. You can count it, but that requires making 2 passes of the data. You can do better. There are some heuristics you might try: for example to guess the length and hope to undershoot. It will either not work in one pass or will not be evenly distributed.

## Simple Solution

A relatively easy and correct solution is to assign a random number to every element as you see it in the stream, and then always keep the top 1,000 numbered elements at all times. This is similar to how a SQL Query with ORDER BY RAND() works. This strategy works well, and only requires additionally storing the randomly generated number for each element.

## Reservoir Sampling

Another, more complex option is **reservoir sampling**. First, you want to make a reservoir (array) of 1,000 elements and fill it with the first 1,000 elements in your stream. That way if you have exactly 1,000 elements, the algorithm works. This is the base case.

Next, you want to process the **i'th** element (starting with **i = 1001**) such that at the end of processing that step, the 1,000 elements in your reservoir are randomly sampled amongst the **i** elements you've seen so far.

How can you do this? Start with **i = 1001**. With what probability after the **1001'th** step whould element 1,001 (or any element for that matter) be in the set of 1,000 elements? The answer is easy 1000/1001. (C(999,1000) / C(1000,1001)) Generate a random number between 0 and 1, and if it is less than 1000/1001 you should take element **1,001**. In other words, choose to add element **1,001** to your reservoir with probability 1000/1001. If you choose to add it, then replace any element in the reservoir chosen randomly.

We've seen that this produces a 1000/1001 chance of selecting the **1,001'th** element, but what about the **2nd** element in the list? Since it's already in the reservoir, so the probability of removing it is the probability of **1,001** element selected multiplied by the probability of **2nd** getting randomly chosen as the replacement candidate. The probability is thus :

    1000/1001 * 1/1000 = 1/1001

So, the probability that the **2nd** element survives this round is :

    1 - 1/1001 = 1000/1001

That's exactly what we want.

This can be extended for the **i'th** round : keep the **i'th** element with probability **1000/i** and if it is kept, replace a random element from the reservoir.

```java
// A function to randomly select k items from stream[0..n-1].
    public static void selectKItems(int stream[], int n, int k) {
        // index for elements in stream[]
        int i;

        // first initialize the reservoir
        int reservoir[] = new int[k];
        for (i = 0; i < k; ++i)
            reservoir[i] = stream[i];

        Random r = new Random();
        for (; i < n; ++i) {
            // pick a random index from 0 to i [0, i]
            int j = r.nextInt(i + 1);

            // If the randomly  picked index is smaller than k,
            // then replace the element present at the index
            // with new element from stream
            if (j < k)
                reservoir[j] = stream[i];
        }
    }
```

How to prove this algorithm is right?

What we want to prove is that for each **i'th** element, the probability that it stays in the reservoir is **k/n**.

So assume the first element is **1'st**, the second **2'**...... **k'th**, ...... **n'th**.

## Let's check for the **k'th** to **n'th** element.

So Last element, which is **n'th** element:

    p(i = n) = k/n
    //that's how we do each round, set the probability to k/i

For the Last second element, which is **(n-1)'th** element:

    p(i = n - 1) = k/(n-1) * (p(a) + p(b))

If **(n-1)'th** element is selected, then whether it stays in the reservoir is decided by the **n'th** element. The first part :

    p(a) = 1 - k/n
    // this is when n'th element is not selected

The second part :

    p(b) = k/n * (1 - 1/k)

This might be tricky, first we do select n'th element so the probability is k/n, then the thing is the next step when we put the **n'th** element into the reservoir, we don't want it to be the **(n-1)'th** position. So the result is (1 - 1/k). After all, the final probability is :

    p(i = n - 1) = k/n

This is exactly what we want! For more elements, the computation is similar.

## Next is 1'th to k'th element

When **i = k + 1**, consider the **jth** element with j belongs to [1, k].

    p(j) = (p(c) + p(d))

Here are two conditions, select **(k+1)'th** or not.

    p(c) = k/(k+1) * (k-1)/k
         = (k-1)/(k+1)

If not select,

    p(d) = 1 - k/(k+1)
         = 1/(k+1)

So after the **i = k + 1** element,

    p(j) = k/(k+1)

You can just continue with **i = k + 2** and so on, then the probability is like this :

    p(j) = k/(k+1) * (k+1)/(k+2) * ...... (n-1)/n
         = k/n

That's what we want!

So now we've proved all element probability is **k/n**.
