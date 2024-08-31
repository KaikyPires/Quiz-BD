gsap.fromTo(
  ".loading-page",
  { opacity: 1 },
  {
    opacity: 0,
    display: "none",
    duration: 1.5,
    delay: 3.5,
    onComplete: () => {
      // Redirecionar para outra página após a animação
      window.location.href = 'Inicio.html'; // Substitua com o URL da sua página principal
    },
  }
);

gsap.fromTo(
  ".logo-name",
  {
    y: 50,
    opacity: 0,
  },
  {
    y: 0,
    opacity: 1,
    duration: 2,
    delay: 0.5,
  }
);
document.getElementById('startButton').addEventListener('click', function() {
  var svgElement = document.getElementById('animatedSvg');
  svgElement.classList.add('animate');
  // Adicione aqui qualquer outro código necessário para iniciar a animação
});

document.getElementById("redirect-button").addEventListener("click", function() {
  window.location.href = "escolhas.html";
});
function menuOnClick() {
  document.getElementById("menu-bar").classList.toggle("change");
  document.getElementById("nav").classList.toggle("change");
  document.getElementById("menu-bg").classList.toggle("change-bg");
}