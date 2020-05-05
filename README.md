# E-Commerce Web App

This is the web application for e-commerce that will have a super user side, product seller side and a user side. The super user will be able to confirm the product seller when they registered in the system and also be able to design the user and product seller sides' pages in terms of carousel image or so on . Furthermore, the super user will decide the category and subcategories that product seller side will sell their products in that categories. That's to say, product seller will not be able to create a new category or subcategory. They just can choose the categories that their products are related with. So, product seller can add their products to system with some information such as products’ name, brand, cost and features of products. As you can guess, the user side will be able to choose product, add it to cart, list the products that are in the same subcategory in terms of the feature etc.

Now, the capability of the application is the CRUD operation of product, product category, product subcategory, subcategory feature and product feature detail that are explained in detail below.

Swagger is also connected. When project is started, go to this ``` http://localhost:8080/swagger-ui/ ``` url to see E-Commerce Application API. Swagger UI page is authenticated with JDBC authentication. Only the user having auth role in db can access to swagger ui page. Passwords of the users are stored as hashed one.

## Technologies that I have been using;
-	Spring Boot
-   Spring Security
-	Hibernate
-	PostgreSQL
-	Restful API
-   Swagger OpenAPI

## Table Of Contents

### [1. API’s and their definitions](https://github.com/tahademir95/ecommerce-backend#1-apis-and-their-definitions-1)
### [2. How to save data into the database from the beginning?](https://github.com/tahademir95/ecommerce-backend#2-how-to-save-data-into-the-database-from-the-beginning-1)

## 1. API’s and their definitions
### A. Product-Api

* **GET** ``` /product-api/product/{id} ```

   Gets the specified product information 
   
*  **GET**  ``` /product-api/get-product-list ```
 
     Gets the products and information of them in a list

* **GET** ``` /product-api/get-product-list-in-the-same-subcategory/{psc_id} ```

   * Gets all products that are in the same subcategory list.
   * User can get all products of any subcategory in the price range that he/she has set. If minimum or maximum cost is not set, then minimum cost will be zero and maximum cost will be 99999. If both of them are not set, then user will get all products in the same subcategory. Syntax is:
   
      ``` /product-api/get-product-list-in-the-same-subcategory/{psc_id}?minCost={minCost}&maxCost={maxCost} ```
   
   * User can get all products of brands that he/she determined in any subcategories
   
      ``` /product-api/get-product-list-in-the-same-subcategory/{psc_id}?brandName={brand_name} ```
      
   * User can get all products of brands and products in the price range in the same subcategory which have been determined by the user. If none of these are specified, then user will get all products in the specified subcategory. If some of these are specified, then user will get the products that are related to what he/she has specified.(User is able to specify more than one brand name)
   
      ``` /product-api/get-product-list-in-the-same-subcategory/{psc_id}?minCost={minCost}&maxCost={maxCost}&brandName={brand_name}&brandName={brand_name} ```

* **GET** ``` /product-api/get-product-list-in-the-same-category/{pc_id} ```

   Gets all products that are in the same category.
   
* **GET** ``` /get-brands-and-count-of-brands-product-in-the-same-subcategory/{psc_id} ```

   Gets all brand names and count of the brands' products under the same subcategory
   
* **GET** ``` /get-brands-and-count-of-brands-product-in-the-same-category/{pc_id} ```

   Gets all brand names and count of the brands' products under the same category
   
*  **Delete** ``` /product-api/delete-product/{id} ```

   Deletes the specified product.
   
*  **Put** ``` /product-api/update-product ```
   Updates product. Syntax is below:
   
   ```JS
   {
	"p_id":id_of_which_product_you_update,
    "productName": "updated_product_name",
    "brandOfProduct": "updated_product_brand",
    "productFee": updated_product_cost,
    "productCategory": {
        "pc_id": updated_product_category
    },
    "subCategory": {
        "psc_id": updated_product_subcategory
    },
    "featureDetails": [
        {
            "pfd_id": updated_product_feature_detail_id
        },
        {
            "pfd_id": …
        }
      ]
    }   
    ```
     
  *  **POST**  ``` /product-api/create-product ```
  
     Creates new product. Syntax is below:
   
   ```JS
   {
      "productName": "new_product_name", 
      "brandOfProduct": "product_of_brand",
      "productFee": product_fee,
      "productCategory": {
         "pc_id": product_category_id
      },
      "subCategory": {
         "psc_id": product_subcategory_id
      },
      "featureDetails":[
          {
             "pfd_id": product_feature_detail_id
          }
      ]
   }
   ```
   
   ### B. Product-Category-Api
   
  * **GET** ``` /product-category-api/product-category/{id} ```
   
      Gets the specified product category information 
      
  * **GET** ``` /product-category-api/get-product-category-list ```
    
       Gets the product categories and information of them in a list
      
  * **DELETE** ``` /product-category-api/delete-product-category/{id} ```
  
     Deletes the specified product category.
     
 * **PUT** ``` /product-category-api/update-product-category ```
 
    Updates product category. Syntax is below:
    
    ```JS
    {
       "pc_id": specify_id_of_which_category_you_want_to_update,
       "categoryName": "update_name_of_the_category"
    }
    ```
   
* **POST** ``` product-category-api/create-product-category ```

   Creates new product category. Syntax is below:
   
   ```JS
   {
      "categoryName": "new_category_name"
   }
   ```
### C. Product-Feature-Detail-Api

* **GET** ``` /product-feature-detail-api/product-feature-detail/{id} ```

   Gets the specified product feature detail information
   
* **GET** ``` /product-feature-detail-api/get-product-feature-detail-list ```  
 
    Gets the product feature detail list and information of them.
   
* **DELETE** ``` /product-feature-detail-api/delete-product-feature-detail/{id} ```

   Deletes the specified product feature detail.
   
* **PUT** ``` /product-feature-detail-api/update-product-feature-detail ```

   Updates product category. Syntax is below:
   
   ```JS
   {
      "pfd_id": specify_id_of_which_product_feature_detail_you_want_to_update,
      "productFeatureDetail": "updated_product_feature_detail",
      "subCategoryFeature":{
         "scf_id": specify_which_subcategory_feature_it_belongs_to
      }
   }
   ```
    
 * **POST** ``` /product-feature-detail-api/create-product-feature-detail ``` 
 
    Creates new product feature detail. Syntax is below:
    
    ```JS
    {
       "productFeatureDetail": "new_product_feature_detail",
       "subCategoryFeature": {
          "scf_id": specify_which_subcategory_feature_it_belongs_to
       }
    }
    ```

 ### D. Product-SubCategory-Api  
   
* **GET** ``` /product-subcategory-api/product-subcategory/{id} ```

   Gets the specified product subcategory information
   
* **GET** ``` /product-subcategory-api/get-product-subcategory-list ```
   
   Gets the product subcategory list and information of them
   
* **DELETE** ``` /product-subcategory-api/delete-product-subcategory/{id} ```

   Deletes the specified product subcategory
   
* **PUT** ``` /product-subcategory-api/update-product-subcategory ```

  Updates product category. Syntax is below:
  
  ```JS
  {
     "psc_id": specify_which_subcategory_you_want_to_update,
     "subCategoryName": "updated_subcategory_name",
     "productCategory": {
        "pc_id": specify_which_category_it_belongs_to
     },
     "subCategoryFeatures": [
        {
           "scf_id": specify_which_feature(s)_it_has
        }
     ]
  }
  ```
   
* **POST** ``` /product-subcategory-api/create-product-subcategory ```  

   Creates new product feature detail. Syntax is below:
   
   ```JS
   {
      "subCategoryName": "new_subcategory_name",
      "productCategory": {
         "pc_id": specify_which_category_it_will_belong_to
      }
   }
   ```
### F. SubCategory-Feature-Api   

* **GET** ``` /subcategory-feature-api/subcategory-feature/{id} ```

   Get the specified subcategory feature information
   
* **GET** ``` /subcategory-feature-api/get-subcategory-feature-list ```
  
     Gets the subcategory feature list and information of them
   
* **DELETE** ``` /subcategory-feature-api/delete-subcategory-feature/{id} ```

   Deletes the specified subcategory feature.
   
* **PUT** ``` /subcategory-feature-api/update-subcategory-feature ```

  Updates product category. Syntax is below:
  
  ```JS
  {
     "scf_id": specify_which_subcategory_feature_you_want_to_update,
     "subCategoryFeatureName": "updated_subcategory_feature_name",
     "subCategory": {
        "psc_id": specify_which_sub_category_it_belongs_to
     }
  }
  ```
     
* **POST** ``` /subcategory-feature-api/create-subcategory-feature ```
 
    Creates new subcategory feature. Syntax is below:
    
    ```JS
    {
       "subCategoryFeatureName": "new_subcategory_feature_name",
       "subCategory": {
          "psc_id": specify_which_sub_category_it_belongs_to
       }
    }
    ```
   
## 2. How to save data into the database from the beginning?

### A. **POST** ``` /product-category-api/create-product-category ```

Firstly, you have to add a category into the database.

```JS
{
   "categoryName": "Cell Phones & Accessories"
}
```

### B. **GET** ``` /product-category-api/get-product-category-list ```

To see the list of the categories that you have added and learn their {id}s. When added subcategories to related category, the subcategories that you saw above will enlarge.

```JS
[
   {
      "pc_id": 42,
      "categoryName": "Cell Phones & Accessories",
      "subCategories": []
   }
]
```

### C. **POST** ``` /product-subcategory-api/create-product-subcategory ```

Then, you should specify which category that we will add the sub-category under of.

```JS
{
   "subCategoryName": "Cell Phones",
   "productCategory": {
      "pc_id": 42
   }
}
```

### D. **GET** ``` /product-subcategory-api/get-product-subcategory-list ```

To see the subcategory list and learn the ids of them. Also when added subcategory feature that should be related to subcategory, you will be able to see the subcategory features of any subcategory.

```JS
[
   {
      "psc_id": 43,
      "subCategoryName": "Cell Phones",
      "subCategoryFeatures": []
   }
]
```

 * When you get request to ``` /product-category-api/get-product-category-list ``` again, you will get;
 
    ```JS
    {
       "pc_id": 42,
       "categoryName": "Cell Phones & Accessories",
       "subCategories": [
          {
             "psc_id": 43,
             "subCategoryName": "Cell Phones",
             "subCategoryFeatures": []
          }
       ]
    }
    ```
    
    You should realize that **subcategories** field enlarge after adding information to product subcategory.

### E. **POST** ``` /subcategory-feature-api/create-subcategory-feature ```

The general features of the added subcategory are added. For instance, if the added subcategory is telephone, then the features of the subcategory that will be added can be the phone's front camera, phone size, etc. In order to add a new feature for a subcategory, you must give the id of that subcategory to indicate which subcategory it belongs to.  

```JS
{
   "subCategoryFeatureName": "Front Camera",
   "subCategory": {
      "psc_id": 43
   }
}
```

### F. **GET** ``` /subcategory-feature-api/get-subcategory-feature-list ```

To see the subcategory feature list and learn the {id}s of them. Also when added product feature detail that should be related to subcategory, you will be able to see the product features of any subcategory.

```JS
[
   {
      "scf_id": 44,
      "subCategoryFeatureName": "Front Camera",
      "productFeatureDetails": []
   }
]
```


 * When you get request to ``` /product-subcategory-api/get-product-subcategory-list ``` again, you will get;

   ```JS
   [
     {
        "psc_id": 43,
        "subCategoryName": "Cell Phones",
        "subCategoryFeatures": [
           {
              "scf_id": 44,
              "subCategoryFeatureName": "Front Camera",
              "productFeatureDetails":[]
           }
        ]
     }
   ]
   ```
   
   You should realize that **subCategoryFeatures** field enlarge after adding information to subcategory feature.

### G. **POST** ``` /product-feature-detail-api/create-product-feature-detail ```

The features of the products are added under the features of the subcategory. That’s to say, if we follow our case, we have added phone’s front camera to subcategory. Now, specify the feature of front cameras such as 8MP or 12MP… To do this, we must specify the subcategory feature id.

```JS
{
   "productFeatureDetail": "8 MP",
   "subCategoryFeature": {
      "scf_id": 44
   }
}
```

### H. **GET** ``` /product-feature-detail-api/get-product-feature-detail-list ```
   
To see the product feature detail list and {id}s of product feature details.

```JS
[
   {
      "pfd_id": 45,
      "productFeatureDetail": "8MP"
   }
]
```

 * When you get request to ``` /subcategory-feature-api/get-subcategory-feature-list ``` again, you will get;

    ```JS
   [
     {
        "scf_id": 44,
        "subCategoryFeatureName": "Front Camera",
        "productFeatureDetails": [
           {
              "pfd_id": 45,
              "productFeatureDetail": "8 MP"
           }
        ]
      }
   ]
   ```
   
   You should realize that **productFeatureDetails** field enlarge after adding information to product feature detail.
   
### I. **POST** ``` /product-api/create-product ```

Finally, we can add a product by specifying it’s name, brand, cost, under which category it will, under which subcategory it will and which feature detail it has.

```JS
{
   "productName": "Galaxy A20",
   "brandOfProduct": "Samsung",
   "productFee": 1560,
   "productCategory": {
      "pc_id": 42
   },
   "subCategory": {
      "psc_id": 43
   },
   "featureDetails": [
      "pfd_id":45
   ]
}
```

### J. **GET** ``` /product-api/get-product-list ```

You can see all the products in a detail as below. 

```JS
[
   {
      "p_id":46,
      "productName": "Galaxy A20",
      "brandOfProduct": "Samsung",
      "productFee": 1560,
      "productCategory": {
         "pc_id": 42,
         "categoryName": "Cell Phones & Accessories"
      },
      "subCategory": {
         "psc_id": 43,
         "subCategoryName": "Cell Phones"
      },
      "featureDetails": [
         {
            "pfd_id": 45,
            "productFeatureDetail": "8 MP"
         }
      ]
   }
]
```
