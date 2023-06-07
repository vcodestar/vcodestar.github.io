##################################

# download train.csv here: https://www.kaggle.com/datasets/iabhishekofficial/mobile-price-classification

#################################

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.metrics import confusion_matrix
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Dense
from sklearn.cluster import KMeans


data = pd.read_csv('train.csv')
ground_truth_labels = data['price_range'].values

input_data = data.iloc[:, :-1].values
scaler = MinMaxScaler()
input_data = scaler.fit_transform(input_data)

input_dim = input_data.shape[1]

encoding_dim = int(input("Enter encoding dimensions(2, 10 or 50):"))
while encoding_dim != 2 and encoding_dim !=10 and encoding_dim !=50:
    encoding_dim = int(input("Enter encoding dimensions(2, 10 or 50):"))


input_layer = Input(shape=(input_dim,))
encoder = Dense(20, activation='relu')(input_layer)
encoder = Dense(100, activation='relu')(encoder)  # Add a layer with 20 units
encoder = Dense(encoding_dim, activation='relu')(encoder)
decoder = Dense(100, activation='relu')(encoder)  # Add a layer with 20 units
decoder = Dense(20, activation='relu')(decoder)
decoder = Dense(input_dim, activation='sigmoid')(decoder)
autoencoder = Model(inputs=input_layer, outputs=decoder)

autoencoder.compile(optimizer='adam', loss='mean_squared_error')
autoencoder.fit(input_data, input_data, epochs=100, batch_size=32)

encoder_model = Model(inputs=input_layer, outputs=encoder)
encoded_data = encoder_model.predict(input_data)

if encoding_dim == 2:
    pca = PCA(n_components=2)
    encoded_data = pca.fit_transform(encoded_data)

    plt.scatter(encoded_data[:, 0], encoded_data[:, 1])
    plt.xlabel('Dimension 1')
    plt.ylabel('Dimension 2')
    plt.title('Encoded Data Visualization')
    plt.show()

if encoding_dim == 50:
    encoded_data = np.repeat(encoded_data, 4, axis=1)



kmeans = KMeans(n_clusters = 6, n_init = 10)  # Replace K with the desired number of clusters
kmeans.fit(encoded_data)

predicted_labels = kmeans.labels_

cluster_centers = kmeans.cluster_centers_

cluster_majority = {}
for cluster_label in np.unique(predicted_labels):
    cluster_indices = np.where(predicted_labels == cluster_label)[0]
    cluster_labels = data['price_range'].iloc[cluster_indices]
    majority_class = cluster_labels.value_counts().idxmax()
    cluster_majority[cluster_label] = majority_class

# Print the majority class label for each cluster
for cluster_label, majority_class in cluster_majority.items():
    print(f"Cluster {cluster_label + 1}: Majority class = {majority_class}")

unique_labels = np.unique(predicted_labels)

cm = confusion_matrix(ground_truth_labels, predicted_labels)

# Get the number of clusters
num_clusters = cm.shape[0]

# Initialize variables to store TP, FP, and FN for each cluster
tp = [0] * num_clusters
fp = [0] * num_clusters
fn = [0] * num_clusters

# Calculate TP, FP, and FN for each cluster
for cluster_label in range(num_clusters):
    for i in range(num_clusters):
        for j in range(num_clusters):
            if i == cluster_label and j == cluster_label:
                tp[cluster_label] = cm[i, j]
            elif i != cluster_label and j == cluster_label:
                fp[cluster_label] += cm[i, j]
            elif i == cluster_label and j != cluster_label:
                fn[cluster_label] += cm[i, j]

fa_s = 0

# Print the results for each cluster
for cluster_label in range(num_clusters):
    print("Cluster:", cluster_label)
    print("True Positives (TP):", tp[cluster_label])
    print("False Positives (FP):", fp[cluster_label])
    print("False Negatives (FN):", fn[cluster_label])
    precision = 1 / (1 + fp[cluster_label])
    recall = 1 / (1 + fn[cluster_label])
    fa = (1 + 1) / ((1 / precision) + (1 / recall))
    fa_s += fa
    print("precision : ", precision)
    print("recall : ", recall)
    print("Fa : ", fa)
    print()

def calculate_purity(predicted_labels, ground_truth_labels):
    total_samples = len(predicted_labels)
    correctly_classified = 0
    cluster_counts = {}

    for cluster_label in np.unique(predicted_labels):
        cluster_indices = np.where(predicted_labels == cluster_label)[0]
        class_counts = np.bincount(ground_truth_labels[cluster_indices])
        majority_class = np.argmax(class_counts)
        correctly_classified += class_counts[majority_class]
        cluster_counts[cluster_label] = len(cluster_indices)

    purity = correctly_classified / total_samples
    return purity


purity = calculate_purity(predicted_labels, ground_truth_labels)
print("Purity:", purity)
print("F-measure:", fa_s)

plt.scatter(encoded_data[:, 0], encoded_data[:, 1], c=predicted_labels)
plt.xlabel('Dimension 1')
plt.ylabel('Dimension 2')
plt.title('K-means Clustering')
plt.show()


