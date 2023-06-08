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
from sklearn.cluster import KMeans, AgglomerativeClustering
from scipy.stats import mode

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
    encoded_data = np.repeat(encoded_data, 2, axis=1)

num_clusters = 10  # Define the desired number of clusters
clustering = AgglomerativeClustering(n_clusters=num_clusters, linkage='ward')
cluster_labels = clustering.fit_predict(encoded_data)

data['Cluster'] = cluster_labels

cluster_majority = {}
for cluster in range(num_clusters):
    cluster_data = data[data['Cluster'] == cluster]
    majority_class = mode(cluster_data['price_range'])[0][0]  
    cluster_majority[cluster] = majority_class
    print(f"Cluster {cluster+1}:Majority class:", cluster_majority[cluster])

unique_labels = np.unique(cluster_labels)

cm = confusion_matrix(ground_truth_labels, cluster_labels)

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

purity = calculate_purity(cluster_labels, ground_truth_labels)
print("Purity:", purity)
print("F-measure :", fa_s)


plt.scatter(encoded_data[:, 0], encoded_data[:, 1], c=cluster_labels)
plt.xlabel('Dimension 1')
plt.ylabel('Dimension 2')
plt.title('Agglomerative Clustering')
plt.show()
