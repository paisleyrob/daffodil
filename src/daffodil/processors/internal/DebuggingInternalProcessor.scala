package daffodil.processors.internal

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

import org.jdom.Parent
import daffodil.debugger.DebuggingListener
import daffodil.schema.annotation.Annotation
import daffodil.xml.Namespaces
import daffodil.processors.VariableMap
import daffodil.processors.ProcessorResult


/**
 * A wrapper around an InternalProcessor that calls a debugger listener before performing the processing step.
 *
 * @param annotation the annotation associated with this processor
 * @param debugger the listener to call before each step
 * @param processor the underlying processor to be wrapped
 *
 * @see daffodil.debugger.DebuggingListener
 * @version 1
 * @author Alejandro Rodriguez
 */
class DebuggingInternalProcessor(annotation:Annotation,
                         debugger:DebuggingListener,
                         processor:InternalProcessor) extends InternalProcessor{
  
   override def apply(parent:Parent,variables:VariableMap,target:String,namespaces:Namespaces) : VariableMap = {
     debugger step(annotation element,annotation,parent,variables,namespaces,null)
     processor(parent,variables,target,namespaces)
   }
  
}
