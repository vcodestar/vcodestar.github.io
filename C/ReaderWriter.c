#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <semaphore.h>
#define NO_OF_THREADS 10


int count = 0;
int buffer[10] = {0};
pthread_mutex_t mutex;
sem_t semFull; // number of full positions
sem_t semEmpty; // number of empty positions

void * producer(void * args)
{
    int num = 0;
    int index = *(int*) args; // cast void pointer to int pointer

    while(1)
    {
        num = rand() % 10; // generate a random number

        sem_wait(&semEmpty); // if there is no spot left then wait

        pthread_mutex_lock(&mutex);

        printf("Thread [%d] Produced : %d\n", index , num);
        buffer[count] = num;
        count ++;

        pthread_mutex_unlock(&mutex);

        sem_post(&semFull); // increase number of positions with element

    }

}

void * consumer(void * args)
{
    int index = *(int*) args; // cast void pointer to int pointer

    while(1)
    {

        sem_wait(&semFull); // if there is no element in buffer then wait for it to be filled

        pthread_mutex_lock(&mutex);


        printf("Thread [%d] Consumed : %d\n", index , buffer[count - 1]);

        count --;

        pthread_mutex_unlock(&mutex);

        sem_post(&semEmpty); // empty one spot after the element was consumed

        sleep(1);
    }
}

int main()
{
    int i;
    srand(time(NULL));
    pthread_t th[NO_OF_THREADS];

    pthread_mutex_init(&mutex, NULL);

    sem_init(&semEmpty, 0, 10); // set number of empty positions to 10
    sem_init(&semFull, 0, 0); // set number of occupide positions to 0
    
    
    for(i = 0; i < NO_OF_THREADS; i++)
    {
        int * a = (int*)malloc(sizeof(int));
        *a = i;

        if(i % 2 == 0)
            pthread_create(&th[i], NULL, &consumer, a);
        else 
            pthread_create(&th[i], NULL, &producer, a);
    }

    for(i = 0; i < NO_OF_THREADS; i++)
    {
        pthread_join(th[i], NULL);
    }

    sem_destroy(&semEmpty);
    sem_destroy(&semFull);

    pthread_mutex_destroy(&mutex);

    return 0;
}
