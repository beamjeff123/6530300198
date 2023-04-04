package pacman;

import java.util.HashSet;
import java.util.Set;

public class Ghost extends Componant{
    public Ghost(int x,int y){

        this.x=x;
        this.y=y;
        direction='L';
    }

    public boolean choice(){
        if(x % cellSize == 0 && y % cellSize == 0)
            return true;
        else
            return false;
    }

    public char selectDirection(){
        int random;
        int newx=x,newy=y;
        Set<Character> mySet= new HashSet<Character>();
        char backwards ='R';

        switch(direction){

            case 'L':
                backwards='R';
                break;
            case 'R':
                backwards='L';
                break;
            case 'U':
                backwards='D';
                break;
            case 'D':
                backwards='U';
                break;
        }

        char newDiection = backwards;

        while (newDiection == backwards || !isValid(newx, newy)){

            if(mySet.size() == 3)
            {
                mySet.clear();
                newDiection=backwards;
                break;
            }

            random = (int)(Math.random() *4) +1;

            if(random == 1){
                newDiection = 'L';
                newx-=speed;
            }
            else if (random == 2)
            {
                newDiection = 'R';
                newx+=cellSize;
            }
            else if (random == 3)
            {
                newDiection = 'U';
                newy-=speed;
            }
            else if (random == 4)
            {
                newDiection = 'D';
                newy+=cellSize;
            }

            if(newDiection != backwards)
            {
                mySet.add(newDiection);
            }

            index = random % 2;
        }
        return newDiection;
    }

    public void move(){

        if(choice()){
            direction= selectDirection();
        }

        switch(direction){
            case 'L':
                if(isValid(x-speed, y))
                    x-=speed;
                break;
            case 'R':
                if(isValid(x+cellSize, y))
                    x+=speed;
                break;
            case 'U':
                if(isValid(x, y-speed))
                    y-=speed;
                break;
            case 'D':
                if(isValid(x, y+cellSize))
                    y+=speed;
                break;
        }
    }
}
