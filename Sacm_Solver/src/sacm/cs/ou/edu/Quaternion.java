package sacm.cs.ou.edu;

public class Quaternion 
{
	float x,y,z; //unit direction vector components
	float w; //magnitude of the rotation vector
	
	public Quaternion (float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	// normalising a quaternion works similar to a vector. This method will not do anything
	// if the quaternion is close enough to being unit-length.
	public void normalise ()
	{
		// Don't normalize if we don't have to
		double mag2 = w * w + x * x + y * y + z * z;
		
		final float TOLERANCE = .00001f;
		
		if (Math.abs(mag2) > TOLERANCE && Math.abs(mag2 - 1.0f) > TOLERANCE) 
		{
			float mag = (float)Math.sqrt(mag2);
			w /= mag;
			x /= mag;
			y /= mag;
			z /= mag;
		}
	}
	
	// We need to get the inverse of a quaternion to properly apply a quaternion-rotation to a vector
	// The conjugate of a quaternion is the same as the inverse, as long as the quaternion is unit-length
	public Quaternion getConjugate ()
	{
		return new Quaternion(-x, -y, -z, w);
	}

	// Multiplying q1 with q2 applies the rotation q2 to q1
	public Quaternion multiplyQuaternion (Quaternion lq, Quaternion rq)
	{
		// the constructor takes its arguments as (x, y, z, w)
		return new Quaternion(	lq.w * rq.x + lq.x * rq.w + lq.y * rq.z - lq.z * rq.y,
								lq.w * rq.y + lq.y * rq.w + lq.z * rq.x - lq.x * rq.z,
								lq.w * rq.z + lq.z * rq.w + lq.x * rq.y - lq.y * rq.x,
								lq.w * rq.w - lq.x * rq.x - lq.y * rq.y - lq.z * rq.z);
	}

	// Multiplying q1 with q2 applies the rotation q2 to q1
	public Quaternion multiplyQuaternion (Quaternion rq)
	{
		return multiplyQuaternion(this,rq);
	}
	
	// Multiplying a quaternion q with a vector v applies the q-rotation to v
	public Vector3D multiplyVector (Vector3D vec)
	{
		Vector3D vn = (vec);
		vn.normalize();
	 
		Quaternion vecQuat = new Quaternion(vn.x,vn.y,vn.z,0f);
		Quaternion resQuat;
	 
		resQuat = multiplyQuaternion(vecQuat, getConjugate());
		resQuat = multiplyQuaternion(resQuat);
	 
		return (new Vector3D(resQuat.x, resQuat.y, resQuat.z));
	}
	
	// Convert from Axis Angle
	static public Quaternion fromAxis(Vector3D v, float angle)
	{
		Vector3D vn = (v);
		vn.normalize();
	 
		float sinAngle = (float)Math.sin(angle/2);
	 
		float x = (vn.x * sinAngle);
		float y = (vn.y * sinAngle);
		float z = (vn.z * sinAngle);
		float w = (float)Math.cos(angle/2);
		
		return (new Quaternion(x,y,z,w));
	}
	
	// Convert to Axis/Angles	
	/*
	 * Returns the angle of rotation
	 */
	public float getAngle ()
	{	
		return (float)Math.acos(w) * 2.0f;
	}
	
	/*
	 * returns the x component of the rotation vector.
	 */
	public float getX ()
	{
		float scale = (float) Math.sqrt(x * x + y * y + z * z);
		return (scale == 0)? 0 : x / scale;
	}
	
	/*
	 * returns the y component of the rotation vector.
	 */
	public float getY ()
	{
		float scale = (float) Math.sqrt(x * x + y * y + z * z);
		return (scale == 0)? 0 : y / scale;
	}
	
	/*
	 * returns the z component of the rotation vector.
	 */
	public float getZ ()
	{
		float scale = (float) Math.sqrt(x * x + y * y + z * z);
		return (scale == 0)? 0 : z / scale;
	}
	
	public String toString ()
	{
		return "("+getX()+","+getY()+","+getZ()+") theta: "+getAngle();
	}
}

