package sacm.cs.ou.edu;


//import javax.microedition.khronos.opengles.GL10;

public class RubiksCube {
	Cube[][][] chickens = new Cube[3][3][3];


	float currRot = 0f;

	Colors r = Colors.RED;
	Colors g = Colors.GREEN;
	Colors b = Colors.BLUE;
	Colors y = Colors.YELLOW;
	Colors o = Colors.ORANGE;
	Colors w = Colors.WHITE;
	Colors k = Colors.BLACK;


	public RubiksCube(){
		// front right top back left bottom
		chickens[0][0][0] = new Cube(new Colors[] {r,k,g,k,y,k});
		chickens[0][0][1] = new Cube(new Colors[] {r,k,g,k,k,k});
		chickens[0][0][2] = new Cube(new Colors[] {r,w,g,k,k,k});

		chickens[0][1][0] = new Cube(new Colors[] {r,k,k,k,y,k});
		chickens[0][1][1] = new Cube(new Colors[] {r,k,k,k,k,k});
		chickens[0][1][2] = new Cube(new Colors[] {r,w,k,k,k,k});

		chickens[0][2][0] = new Cube(new Colors[] {r,k,k,k,y,b});
		chickens[0][2][1] = new Cube(new Colors[] {r,k,k,k,k,b});
		chickens[0][2][2] = new Cube(new Colors[] {r,w,k,k,k,b});

		chickens[1][0][0] = new Cube(new Colors[] {k,k,g,k,y,k});
		chickens[1][0][1] = new Cube(new Colors[] {k,k,g,k,k,k});
		chickens[1][0][2] = new Cube(new Colors[] {k,w,g,k,k,k});

		chickens[1][1][0] = new Cube(new Colors[] {k,k,k,k,y,k});
		chickens[1][1][1] = new Cube(new Colors[] {k,k,k,k,k,k});
		chickens[1][1][2] = new Cube(new Colors[] {k,w,k,k,k,k});

		chickens[1][2][0] = new Cube(new Colors[] {k,k,k,k,y,b});
		chickens[1][2][1] = new Cube(new Colors[] {k,k,k,k,k,b});
		chickens[1][2][2] = new Cube(new Colors[] {k,w,k,k,k,b});

		chickens[2][0][0] = new Cube(new Colors[] {k,k,g,o,y,k});
		chickens[2][0][1] = new Cube(new Colors[] {k,k,g,o,k,k});
		chickens[2][0][2] = new Cube(new Colors[] {k,w,g,o,k,k});

		chickens[2][1][0] = new Cube(new Colors[] {k,k,k,o,y,k});
		chickens[2][1][1] = new Cube(new Colors[] {k,k,k,o,k,k});
		chickens[2][1][2] = new Cube(new Colors[] {k,w,k,o,k,k});

		chickens[2][2][0] = new Cube(new Colors[] {k,k,k,o,y,b});
		chickens[2][2][1] = new Cube(new Colors[] {k,k,k,o,k,b});
		chickens[2][2][2] = new Cube(new Colors[] {k,w,k,o,k,b});


		

	}

	public void swapper(int x1, int y1, int z1, int x2, int y2, int z2){
		Cube temp = chickens[x1][y1][z1];
		chickens[x1][y1][z1] = chickens[x2][y2][z2];
		chickens[x2][y2][z2] = temp;
	}
	public void rotate(boolean left_right,int type, int dist){
		
		
		switch(type){
		case 0:
			//currRot+=9;
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					
					chickens[dist][x][y].setRotation(270, new boolean[]{false, false, true});
					
					
				}
				
				
			}
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					{
						swapper(dist, 0,0,dist, 0, 2);
						swapper(dist, 0,0,dist, 2, 2);
						swapper(dist, 0,0,dist, 2, 0);

						swapper(dist, 1,0,dist, 0, 1);
						swapper(dist, 1,0,dist, 1, 2);
						swapper(dist, 1,0,dist, 2, 1);
					}
				}
			}
				break;
		case 1:
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					chickens[x][dist][y].setRotation(270, new boolean[]{false, true, false});
				}
			}
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					{
						swapper(0, dist,0,2, dist, 0);
						swapper(0, dist,0, 2, dist, 2);
						swapper(0, dist,0,0, dist, 2);

						swapper(1, dist,0,2, dist, 1);
						swapper(1, dist,0,1, dist, 2);
						swapper(1, dist,0,0, dist, 1);
					}
				}
			}
			
			break;
		case 2:
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					chickens[x][y][dist].setRotation(270, new boolean[]{true, false, false});
				}
			}
			
			for(int x = 0; x<3;x++){
				for(int y = 0; y<3; y++){
					{
						swapper(0, 0,dist,2, 0,dist);
						swapper(0, 0,dist, 2, 2,dist);
						swapper(0, 0,dist,0, 2,dist);

						swapper(1, 0,dist,2, 1,dist);
						swapper(1, 0,dist,1, 2,dist);
						swapper(1, 0,dist,0, 1,dist);
					}
				}
			}
			
			break;
			
		}
	}

}
