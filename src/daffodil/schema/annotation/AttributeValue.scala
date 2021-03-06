package daffodil.schema.annotation


/**
 * Copyright (c) 2010 NCSA.  All rights reserved.
 * Developed by: NCSA Cyberenvironments and Technologies
 *               University of Illinois at Urbana-Champaign
 *               http://cet.ncsa.uiuc.edu/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal with the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimers.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimers in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the names of NCSA, University of Illinois, nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     Software without specific prior written permission.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * WITH THE SOFTWARE.
 *
 */

/*
 * Created By: Alejandro Rodriguez < alejandr @ ncsa . uiuc . edu >
 * Date: 2010
 */


import java.io.Serializable
import daffodil.parser.regex.Regex

/**
 * Holds the value of DFDL properties that can be multivalued (described in the spec as space-separated lists).
 *
 * @version 1
 * @author Alejandro Rodriguez
 */
@SerialVersionUID(1)
sealed abstract class AttributeValue extends Serializable
@SerialVersionUID(1)
case class ExpressionValue(s:String) extends AttributeValue
@SerialVersionUID(1)
case class ListLiteralValue(l:List[Regex]) extends AttributeValue {
  override def toString = {
    l map ((r : Regex) => r.toString) reduce ((x : String, y : String) => x + " " + y)
  }
  
  override def equals(o:Any) = {
    o match {
      case ListLiteralValue(xl) => {
        l.toString == xl.toString // let's compare Regex's by their string reps.
      }
      case _ => false
    }
  }
}
@SerialVersionUID(1)
case object EmptyValue extends AttributeValue
