const hello = () => alert("it works");
hello();
async function logProduct() {
  const response = await fetch("/api.products");
  const movies = await response.json();
  console.log(products);
}