import React, { useRef, useEffect } from "react";
import p5 from "p5";

const Sketch = () => {
  const sketchRef = useRef(null);

  useEffect(() => {
    createSketch();
    console.log(sketchRef)
    // Retorna uma função de limpeza para destruir o sketch
    // sketchRef.current.remove();
  }, []);

  const createSketch = () => {
    const sketch = new p5((p) => {
      p.setup = () => {
        let canvas = p.createCanvas(1100, 395);
        canvas.position(440, 230);
      };

      p.draw = () => {
        p.background(255);
        const strongGreen = p.color("#009039");
        const green = p.color("#27B55F");
        const red = p.color("#FF4848");
        const white = p.color(255);

        //jumbo reel
        p.fill(red);
        p.rect(0, 0, 1100, 75);

        //information box
        p.stroke(green);
        p.fill(white);
        p.rect(0, 0, 70, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("Qtd.", 35, 27);
        p.text("8x", 35, 45);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(70, 0, 380, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("450 mm", 170, 40);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(270, 0, 250, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("500 mm", 376, 40);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(480, 0, 280, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("100 mm", 539, 40);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(600, 0, 440, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("900 mm", 820, 40);

        //3º value from waste = 1100 - sum of 3º values from rect. The 1º is the sum of the previus 1º and 3º
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("50 mm", 1070, 40);

        //line
        p.strokeWeight(2);
        p.stroke(0);
        p.line(72, 80, 1040, 80);
        p.noStroke();
        p.strokeWeight(1);
        p.text("2000 mm", 520, 90);

        //jumbo reel
        p.fill(red);
        p.rect(0, 100, 1100, 75);

        //information box
        p.stroke(green);
        p.fill(white);
        p.rect(0, 100, 150, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("Qtd.", 35, 130);
        p.text("4x", 35, 150);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(70, 100, 370, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("450 mm", 170, 140);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(270, 100, 330, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("500 mm", 376, 140);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(480, 100, 280, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("100 mm", 539, 140);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(600, 100, 440, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("900 mm", 820, 140);

        //3º value from waste = 1100 - sum of 3º values from rect. The 1º is the sum of the previus 1º and 3º
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("50 mm", 1070, 140);

        //line
        p.strokeWeight(2);
        p.stroke(0);
        p.line(72, 180, 1040, 180);
        p.noStroke();
        p.strokeWeight(1);
        p.text("2000 mm", 520, 190);

        //jumbo reel
        p.fill(red);
        p.rect(0, 200, 1100, 75);

        //information box
        p.stroke(green);
        p.fill(white);
        p.rect(0, 200, 70, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("Qtd.", 35, 230);
        p.text("12x", 35, 250);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(70, 200, 370, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("450 mm", 170, 240);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(270, 200, 330, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("500 mm", 376, 240);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(480, 200, 280, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("100 mm", 539, 240);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(600, 200, 440, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("900 mm", 820, 240);

        //3º value from waste = 1100 - sum of 3º values from rect. The 1º is the sum of the previus 1º and 3º
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("50 mm", 1070, 240);

        //line
        p.strokeWeight(2);
        p.stroke(0);
        p.line(72, 280, 1040, 280);

        p.noStroke();
        p.strokeWeight(1);
        p.text("2000 mm", 520, 290);

        //jumbo reel
        p.fill(red);
        p.rect(0, 300, 1100, 75);

        //information box
        p.stroke(green);
        p.fill(white);
        p.rect(0, 300, 70, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("Qtd.", 35, 330);
        p.text("4x", 35, 350);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(70, 300, 370, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("450 mm", 170, 340);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(270, 300, 330, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("500 mm", 376, 340);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(strongGreen);
        p.rect(480, 300, 280, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("100 mm", 539, 340);

        //slice of the jumbo reel with its color, text
        p.stroke(white);
        p.fill(green);
        p.rect(600, 300, 440, 75);
        p.noStroke();
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("900 mm", 820, 340);

        //3º value from waste = 1100 - sum of 3º values from rect. The 1º is the sum of the previus 1º and 3º
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(13);
        p.text("50 mm", 1070, 340);

        //line
        p.strokeWeight(2);
        p.stroke(0);
        p.line(72, 380, 1040, 380);
        p.noStroke();
        p.strokeWeight(1);
        p.text("2000 mm", 520, 390);
      };
    });

    // Salva a referência do sketch para destruí-lo quando o componente for desmontado
    sketchRef.current = sketch;
  };

  return <div ref={sketchRef}></div>;
};

export default Sketch;
