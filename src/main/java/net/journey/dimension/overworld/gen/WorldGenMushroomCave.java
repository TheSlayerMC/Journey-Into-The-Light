/**
 * package net.journey.dimension.overworld.gen;
 * <p>
 * import java.util.Random;
 * <p>
 * import net.minecraft.block.Block;
 * import net.minecraft.block.material.Material;
 * import net.minecraft.init.Blocks;
 * import net.minecraft.util.BlockPos;
 * import net.minecraft.world.World;
 * import net.minecraft.world.gen.feature.WorldGenerator;
 * <p>
 * public class WorldGenMushroomCave extends WorldGenerator
 * {
 * //boolean generateUnderwater = mod_Shroom.generateCaveUnderwater;
 * boolean hasWater = false;
 * //private WorldGenCyanMushroom genMushroomCyan = new WorldGenCyanMushroom();
 *
 * @Override public boolean generate(World world, Random random, int i, int j, int k)
 * {
 * int x = random.nextInt(2);
 * hasWater = true;
 * if(x == 0) hasWater= false;
 * <p>
 * /**if(!generateUnderwater){
 * for(int j1 = 100; j1 > 40; j1--){
 * if(world.getBlockMaterial(i,j1,k) == Material.water || world.getBlockMaterial(i,j1,k) == Material.lava){
 * return false;
 * }
 * }
 * }
 * <p>
 * if(world.setBlockState(i,j,k) != Block.stone.blockID) return false;
 * <p>
 * //Begins the recursive generation
 * if(random.nextInt(2) == 0)
 * return recursiveGenerate(world,random,i,j,k,11);
 * <p>
 * return recursiveGenerate(world,random,i,j,k,12);
 * }
 * <p>
 * /**Yea boi
 * public boolean recursiveGenerate(World world, Random random, int i, int j, int k, int initialRadius){
 * if(initialRadius < 7){
 * if(random.nextInt(2200)==0){
 * for(int q = 0; q<50; q++){
 * int i1 = random.nextInt(10) - random.nextInt(10);
 * int j1 = random.nextInt(10) - random.nextInt(10);
 * int k1 = random.nextInt(10) - random.nextInt(10);
 * if(world.getBlockState(i+i1, j+j1, k+k1) == 0){
 * WorldGenTickNest gen = new WorldGenTickNest();
 * gen.generate(world, random, i+i1, j+j1, k+k1);
 * break;
 * }
 * }
 * }
 * <p>
 * if(random.nextInt(200)==0){
 * for(int q = 0; q<50; q++){
 * int i1 = random.nextInt(10) - random.nextInt(10);
 * int j1 = random.nextInt(10) - random.nextInt(10);
 * int k1 = random.nextInt(10) - random.nextInt(10);
 * if(world.getBlockState(i+i1, j+j1, k+k1) == 0){
 * WorldGenThorns gen = new WorldGenThorns();
 * gen.generate(world, random, i+i1, j+j1, k+k1);
 * break;
 * }
 * }
 * }
 * <p>
 * return true;
 * }
 * int radius =  initialRadius - 2 + random.nextInt(5);
 * createSphere(world, random, i - 5 + random.nextInt(11), j ,k , radius, 4);
 * for(int c = 0;c<3+random.nextInt(3);c++){
 * int i1 = 0;
 * int j1 = ((12 + random.nextInt(36))*2 + j)/3;
 * int k1 = 0;
 * while(Math.abs(i1) + Math.abs(k1) < radius*3/2 && world.getBlockState(1, 1, 1)!= Blocks.stone.getDefaultState()){
 * i1 += (7 - random.nextInt(15));
 * k1 += (7 - random.nextInt(15));
 * }
 * recursiveGenerate(world,random,i+i1,j1,k+k1,initialRadius - 1);
 * }
 * return true;
 * }
 * <p>
 * /**Creates a single hollow sphere together with the floor and Small Mushrooms.
 * public void createSphere(World world, Random random,int i, int j, int k, int radius, int randomFactor)
 * {
 * //Used for creating mushroom grass floor at the bottom
 * int minimum = -radius - randomFactor/2 + random.nextInt(randomFactor+1);
 * <p>
 * //Nested loop that creates the sphere etc. the random things are just to randomise the shape, all that really matters is radius and -radius
 * for(int i1=-radius - randomFactor/2 + random.nextInt(randomFactor+1); i1<radius - randomFactor/2 + random.nextInt(randomFactor+1); i1++){
 * for(int k1=-radius - randomFactor/2 + random.nextInt(randomFactor+1); k1<radius - randomFactor/2 + random.nextInt(randomFactor+1); k1++){
 * for(int j1= radius - randomFactor/2 + random.nextInt(randomFactor+1); j1>minimum; j1--){
 * <p>
 * //Part which makes the loop cube into a sphere by getting rid of the corners
 * if(Math.pow(i1, 2) + Math.pow(j1, 2) + Math.pow(k1, 2) < Math.pow(radius, 2)){
 * <p>
 * if(canEdit(world,i + i1, j + j1, k + k1)){
 * //Puts set liquid if too close to bottom of world (right now it's lava)
 * if(j+j1 <10 && world.getBlockState(i + i1, j + j1, k + k1) != Block.lavaStill.blockID && world.getBlockState(i + i1, j + j1, k + k1) != Block.lavaMoving.blockID ){
 * if(hasWater)
 * world.setBlock(i + i1, j + j1, k + k1, mod_Shroom.mushroomLiquidFlowing.blockID);
 * else
 * createGround(world,random, i + i1, 9, k + k1, 10);
 * <p>
 * }
 * //Hollowing area and filling in the bottom middle (minimum is used here)
 * else if(j1 == minimum+2 && world.getBlockState(i + i1, j + j1+1, k + k1) == 0 && world.getBlockState(i + i1, j + j1, k + k1) != 0){
 * createGround(world,random, i + i1, j + j1, k + k1, 1);
 * }
 * else{
 * //Hollows out
 * if(j1 != minimum+1 && world.getBlockState(i + i1, j + j1, k + k1) != 0){
 * world.setBlock(i + i1, j + j1, k + k1, 0, 0, 3);
 * }
 * }
 * }
 * }
 * //Putting mushroom grass and dirt etc. onto the side areas
 * else{
 * if(canEdit(world,i + i1, j + j1, k + k1)){
 * if(world.getBlockState(i + i1, j + j1+1, k + k1) == 0 && world.getBlockState(i + i1, j + j1, k + k1) != 0 && world.getBlockState(i + i1, j + j1, k + k1) <256 && j1<6 && j1<minimum +5){
 * <p>
 * //Puts set liquid if too close to bottom of world (right now it's lava)
 * if(j+j1 <10 && world.getBlockState(i + i1, j + j1, k + k1) != Block.lavaStill.blockID && world.getBlockState(i + i1, j + j1, k + k1) != Block.lavaMoving.blockID){
 * if(hasWater)
 * world.setBlock(i + i1, j + j1, k + k1, mod_Shroom.mushroomLiquidFlowing.blockID);
 * else
 * createGround(world,random, i + i1, 9, k + k1, 10);
 * }
 * else {
 * createGround(world,random, i + i1, j + j1, k + k1, 1);
 * }
 * <p>
 * }
 * }
 * }
 * <p>
 * }
 * <p>
 * }
 * }
 * <p>
 * }
 * public void createGround(World world, Random random, int i, int j, int k, int cyanMushroomChance){
 * if(random.nextInt(6) == 0){
 * plantSmallMushroom(world,random,i, j+1, k, cyanMushroomChance);
 * }
 * else if(random.nextInt(120) == 0){
 * plantMediumMushroom(world,random,i, j+1, k);
 * }
 * else if(random.nextInt(4) == 0){
 * if(random.nextInt(20)== 0){
 * world.setBlock(i ,j+1 , k, mod_Shroom.tallGrass.blockID, 0, 3);
 * }
 * else{
 * world.setBlock(i ,j+1 , k, mod_Shroom.tallGrass.blockID, 1, 3);
 * }
 * }
 * <p>
 * world.setBlock(i ,j , k, mod_Shroom.mushroomGrass.blockID);
 * world.setBlock(i, j-1, k, mod_Shroom.mushroomDirt.blockID);
 * }
 * <p>
 * <p>
 * /*Generate a small random mushroom block that can turn into a big one (Blue, Purple, Green)
 * public void plantSmallMushroom(World world, Random random, int i, int j, int k, int cyanMushroomChance){
 * if(genMushroomCyan.canGenerate(world,random,i,j,k,13) && random.nextInt(cyanMushroomChance) == 0){
 * <p>
 * world.setBlock(i,j+2,k,mod_Shroom.generator.blockID , 0, 3);
 * world.setBlock(i,j+1,k,mod_Shroom.mushroomCyanCap.blockID , 0, 3);
 * world.setBlock(i,j,k,mod_Shroom.mushroomCyanStem.blockID , 0, 3);
 * }
 * else if(random.nextInt(30)==0){
 * world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 * world.setBlock(i,j,k,mod_Shroom.purpleMushroom.blockID , 0, 3);
 * }
 * else if(random.nextInt(6)==0){
 * world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 * world.setBlock(i,j,k,mod_Shroom.blueMushroom.blockID, 0, 3);
 * }
 * }
 * /*Generate a small random mushroom block that can turn into a big one (Puffshroom, JumpShroom)
 * public void plantMediumMushroom(World world, Random random, int i, int j, int k){
 * <p>
 * if(random.nextInt(5)==0){
 * world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 * world.setBlock(i, j, k, mod_Shroom.jumpShroomSmall.blockID, 0, 3);
 * }
 * else{
 * world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 * world.setBlock(i, j, k, mod_Shroom.puffShroomSmall.blockID, 0, 3);
 * }
 * <p>
 * }
 * <p>
 * /*Checks if the area has any blocks that should be changed
 * public boolean canEdit(World world, int i, int j, int k){
 * <p>
 * return world.getBlockState(i, j, k) != mod_Shroom.puffShroom.blockID && world.getBlockState(i, j, k) != mod_Shroom.shroomStem.blockID
 * && world.getBlockState(i, j, k) != mod_Shroom.tallGrass.blockID;
 * <p>
 * }
 * @Override public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
 * // TODO Auto-generated method stub
 * return false;
 * <p>
 * }
 * <p>
 * }
 */ /**
 public void plantSmallMushroom(World world, Random random, int i, int j, int k, int cyanMushroomChance){
 if(genMushroomCyan.canGenerate(world,random,i,j,k,13) && random.nextInt(cyanMushroomChance) == 0){

 world.setBlock(i,j+2,k,mod_Shroom.generator.blockID , 0, 3);
 world.setBlock(i,j+1,k,mod_Shroom.mushroomCyanCap.blockID , 0, 3);
 world.setBlock(i,j,k,mod_Shroom.mushroomCyanStem.blockID , 0, 3);
 }
 else if(random.nextInt(30)==0){
 world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 world.setBlock(i,j,k,mod_Shroom.purpleMushroom.blockID , 0, 3);
 }
 else if(random.nextInt(6)==0){
 world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 world.setBlock(i,j,k,mod_Shroom.blueMushroom.blockID, 0, 3);
 }
 }
 /*Generate a small random mushroom block that can turn into a big one (Puffshroom, JumpShroom)*/ /**
 public void plantMediumMushroom(World world, Random random, int i, int j, int k){

 if(random.nextInt(5)==0){
 world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 world.setBlock(i, j, k, mod_Shroom.jumpShroomSmall.blockID, 0, 3);
 }
 else{
 world.setBlock(i,j+1,k,mod_Shroom.generator.blockID , 0, 3);
 world.setBlock(i, j, k, mod_Shroom.puffShroomSmall.blockID, 0, 3);
 }

 }

 /*Checks if the area has any blocks that should be changed */ /**
 public boolean canEdit(World world, int i, int j, int k){

 return world.getBlockState(i, j, k) != mod_Shroom.puffShroom.blockID && world.getBlockState(i, j, k) != mod_Shroom.shroomStem.blockID
 && world.getBlockState(i, j, k) != mod_Shroom.tallGrass.blockID;

 }

 @Override public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
 // TODO Auto-generated method stub
 return false;

 }

 }*/
