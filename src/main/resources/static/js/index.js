// Estado de la paginación
var state = {categories: [], products: [], selectPrecio: "todos", selectCategory: 0}
// Container principal donde se aloja el catálogo e ícono para indicar que está procesando.
var container, loading;
// Número de ítems por fila
const nItems = 3;

function renderOptionsCategory(selectCategory) {
    fetch('/category', {method: 'OPTIONS'})
        .then(response => response.json())
        .then(res => {
            var optNull = document.createElement('option');
            optNull.value = 0;
            optNull.innerHTML = 'Todos';
            selectCategory.appendChild(optNull);
            res.forEach(cat => {
                var opt = document.createElement('option');
                opt.value = cat.idCategory;
                opt.innerHTML = cat.name;
                selectCategory.appendChild(opt);
            })
        });
}

// Listener inicial. Se asignan funciones al input de búsqueda y se setean 
// valores iniciales.
window.addEventListener('load', function(e) {
    container = document.getElementById("idcontainer");
    loading = document.getElementById("idloading")
    var searchButton = document.getElementById("id__search__button");
    var searchInput = document.getElementById("id__search__input");
    var selectPrecio = document.getElementById("id__precios__select");
    var selectCategory = document.getElementById("id__category__select");
    renderOptionsCategory(selectCategory);
    selectCategory.addEventListener('change', e => {
        state.selectCategory = e.target.value;
        getProductsByNameAndByPrice(searchInput.value)
    });
    selectPrecio.addEventListener('change', e => {
        state.selectPrecio = e.target.value;
        getProductsByNameAndByPrice(searchInput.value)
    });
    searchInput.addEventListener('keydown', e => {
        if(e.key=='Enter') {
            e.preventDefault();
            getProductsByNameAndByPrice(searchInput.value)
        }
    });
    getProductsByNameAndByPrice("");
})

//  request de los productos filtrados por nombre
function getProductsByNameAndByPrice(input) {
    loading.style.display = "block";
    fetch(`/products?name=${input.toUpperCase()}&price=${this.state.selectPrecio}&category=${this.state.selectCategory}`)
        .then(res => res.json())
        .then(data => {
            loading.style.display = "none";
            if(data.length==0) {
                renderInfo("No hay coincidencias");
                return;
            }
            return renderCatalogo(data)
        })
        .catch(err => {
            loading.style.display = "none";
            console.log(err)
        })
}

// render mensaje de respuesta
function renderInfo(msg) {
    container.innerHTML = `<div class="alert--info">
                                <div class="alert alert-info" role="alert">
                                    ${msg}
                                </div>
                            </div>`;
}

// renderiza el catálogo de productos.
function renderCatalogo(data) {
    container.innerHTML = '';
    state.categories = Array.from(new Set(data.map(d=>d.category)))
        .sort((c1,c2) => {
            if(c1<c2) {return -1};
            if(c2>c2) {return 1};
            return 0;
        })
        .map(c=>({ name:c,
                   id: data.filter(d=>d.category==c)[0].idcategory,
                   totalItems: data.filter(d=>d.category==c).length,
                   page:1,
                   npage: ~~(data.filter(d=>d.category==c).length/3)
                 }))
    state.products = data;
    renderCategoria();
}

// Renderiza una categoría de productos.
function renderCategoria() {
    state.categories
       .forEach(c=> {
           container.innerHTML += `<H1 class="container__category">${c.name.toUpperCase()}</H1>`
           container.innerHTML += `<div id="category${c.id}" class="catalogo"></div>`
           renderCategoryItem(c);
       })
}

// Renderiza los productos de una categoría.
function renderCategoryItem(c) {
   var category = document.getElementById(`category${c.id}`);
   category.innerHTML = renderIconLeft(c);
   state.products
       .filter(d => d.category == c.name)
       .sort((d1,d2) => {
       if(d1.name<d2.name) {
           return -1;
       } else if(d1.name>d2.name) {
           return 1;
       } else {
           return 0;
       }
       })
       .slice((c.page-1)*nItems,c.page*nItems>=c.totalItems?c.totalItems:c.page*nItems)
       .forEach(d => {
           category.innerHTML += renderCard(d);
       });
   category.innerHTML += renderIconRight(c);
}

// Renderiza una carta con información de un producto.
function renderCard(d) {
if(d.url_image== null || d.url_image=="") {
    d.url_image="/img/SinImagen.jpg";
}
return `<div class="card">
            <img src='${d.url_image}' class="card-img-top" alt="Imagen no Disponible">
            <div class="card-body">
                <h5 class="card-title">${d.name}</h5>
                <a href="#" class="btn btn-primary">$ ${separadorMiles(d.price)}</a>
              </div>
        </div>`;
}

// Renderiza ícono de paginación izquierdo.
function renderIconLeft(c) {
    var opacity = (c.page>1)?"1":"0";
    return `<div id="previousPage${c.id}" style="opacity:${opacity} " onclick="onClickPrevious(event, ${c.id})" class="previousPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

// Renderiza ícono de paginación derecho.
function renderIconRight(c) {
    var opacity = (c.page<=c.npage && c.totalItems>c.page*nItems)?"1":"0";
    return `<div id="nextPage${c.id}" style="opacity:${opacity}" onclick="onClickNext(event, ${c.id})" class="nextPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

// Función ejecutada en el evento Click de la flecha previo.
function onClickPrevious(e,id) {
    let c = state.categories.find(c=>c.id==id)
    var previous = document.getElementById(`previousPage${c.id}`)
    if(c.page>1) c.page--
    renderCategoryItem(c);
}

// Función ejecutada en el evento Click de la flecha siguiente.
function onClickNext(e,id){
    let c = state.categories.find(c=>c.id==id)
    var next = document.getElementById(`nextPage${c.id}`)
    if(c.page<=c.npage && c.totalItems>c.page*nItems) c.page++
    renderCategoryItem(c);
}

// Función regex que entrega números con separador(".") de miles.
function separadorMiles(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}